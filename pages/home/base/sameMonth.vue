<template>
	<view class="same-month page-bg min-height-100">
		<a-navbar title="当月业绩" @back="back"></a-navbar>
		<view class="header header-bg text-white">
			<view class="time flex a-center j-start" @click="show = true">
				{{currentMonth}}
				<view class="right flex-shrink" style="margin-left: 20rpx;">
					<u-icon name="arrow-down"></u-icon>
				</view>
			</view>
			<u-picker v-model="show" max-date='5000' @confirm='change' mode="time" :params="params"></u-picker>
			<view class="header-info" v-if="currentMonthPerformance">
				<view class="text-center">当月分成总业绩</view>
				<view class="text-center count">{{currentMonthPerformance.yeji}}</view>
				<view class="text-center">已收：{{currentMonthPerformance.shishou}}</view>
			</view>
		</view>
		<view class="content-person" v-if="pageType==='person' || isPersonDetail">
			<view class="info bg-white">
				共有{{totalOrder}}个相关报单
			</view>
			<view class="tab-wrap">
				<u-subsection :list="list" :current="personCurrentIndex" @change="personSubsection"></u-subsection>
			</view>
			<view class="card-wrap" v-if="currentMonthPerformance">
				<view class="card bg-white" v-for="(item,index) in currentMonthPerformance.chengjiao" :key='index'>
					<view class="title">
						{{$u.timeFormat(item.shijian, 'yyyy/mm/dd MM:ss')}}
					</view>
					<view class="box flex j-between a-center flex-row">
						<view class="left flex1">
							<view class="line">
								楼盘地址：{{item.lupandizhi}}
							</view>
							<view class="line">
								报单类型：{{item.cjname}}
							</view>
							<view class="line">
								分成比例：{{item.bili}}
							</view>
							<view class="line">
								业绩所属店铺：{{item.guishu}}
							</view>
						</view>
						<view class="right flex-shrink">
							<view class="value">
								{{item.yeji}}
							</view>
							<view>
								已收：{{item.shishou}}
							</view>
						</view>
					</view>
				</view>
			</view>
		
		</view>
		<view class="content-shop-group" v-if="(pageType==='shopGroup' || pageType==='shop') && !isPersonDetail">
			<view class="card-wrap">
				<view class="card bg-white border-bottom flex j-between a-center flex-row" v-for="(item,index) in listArr" :key='index'
					><!-- @click="toPage(item)"-->
					<view class="left flex-shrink">	
						<u-avatar :src="item.touxiang"></u-avatar>
					</view>
					<view class="mid flex1">
						<view class="name bold">
							{{item.cjren}} <text v-if="item.cj_bumen">（{{item.cj_bumen}}）</text>
						</view>
						<view class="line flex a-center j-start flex-row">
							<view>
								<text>月总业绩</text>
								<text class="text-blue">{{item.yeji}}</text>
							</view>
							<view>
								<text class="riie">已收业绩</text>
								<text class="text-blue">{{item.shishou}}</text>
							</view>
						</view>
					</view>
					<!-- <view class="right flex-shrink">
						<u-icon name="arrow-right"></u-icon>
					</view> -->
				</view>
			</view>
		</view>
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				show:false,
				params:{
					year:true,
					month:true,
					day:false,
					hour:false,
					minute:false,
					second:false
				},
				totalOrder:0,
				pageType:"",
				isPersonDetail:false,
				otherUserId:null,
				listArr:[],
				personCurrentIndex:0,
				list: [
					{
						name: '售单'
					},
					{
						name: '租单'
					},{
						name: '新盘'
					}
				],
				current: 0,
				currentMonth:'',
				currentMonthPerformance:null
			}
		},
		onLoad(options){
			this.pageType=options.type
			this.currentMonth=this.$u.timeFormat(String(new Date().getTime()), 'yyyy-mm')
			this.init()
		},
		methods:{
			change(data){
				this.currentMonth = data.year+'-'+data.month
				this.init()
			},
			init(){
				switch(this.pageType){
					case "person":
						this.getPerson()
						this.otherUserId=null
						break
					case "shop":
						this.getShop(3)
						break
					case "shopGroup":
						this.getShop(4)
						break
				}
			},
			personSubsection(val){
				this.personCurrentIndex=val
				this.getPerson()
			},
			getShop(type){
				this.$tool.uniRequest({
					url:"juece/cxzdyeji/",
					method:'GET',
					params:{
						id:this.$tool.uniGetStorage('userId'),
						bumen_t:type,
						datetimes:this.currentMonth,	
					},
					success:(res)=>{
						this.currentMonthPerformance=res
						this.listArr=res.chengjiao
						
					}
				})	
			},
			getPerson(){
				this.$tool.uniRequest({
					url:"juece/cxgryeji/",
					method:'GET',
					params:{
						id:this.pageType==='person' ? this.$tool.uniGetStorage('userId'):this.otherUserId,
						datetimes:this.currentMonth,
						cjtype:this.personCurrentIndex+1
					},
					success:(res)=>{
						this.currentMonthPerformance=res					
						this.totalOrder=res.chengjiao?res.chengjiao.length:0
					}
				})	
			},
			back(){
				if(this.isPersonDetail) {
					this.isPersonDetail=false
					return
				}
				this.$tool.uniRedirectTo({
					url:`/pages/home/base/index?type=${this.pageType}`,
				})
			},		
			toPage(item){
				this.isPersonDetail=true
				this.personCurrentIndex=0
				this.otherUserId=item.id
				this.getPerson()
			}
		}
	}
</script>

<style scoped lang="scss">
	.text-blue{
		margin-left:20rpx;
	}
	.riie{
		margin-left: 40rpx;
	}
	.same-month {
		.header {
			.time {
				padding: 0 20rpx;
			}

			.header-info {
				padding: 20rpx 0;

				.count {
					font-size: 50rpx;
					margin: 20rpx 0;
				}
			}
		}
		.content-person{
			.info{
				padding: 20rpx;
			}
			.tab-wrap{
				margin-bottom: 20rpx;
			}
			.card-wrap{
				.card{
					padding: 20rpx;
					margin: 0 20rpx 20rpx 20rpx;
					border-radius: 8rpx;
					line-height: 1.6;
					.title{
						margin-bottom: 6rpx;
						color: gray;
					}
					.box{						
						.right{
							width: 120rpx;
							padding-right: 6rpx;
							.value{
								color: #6E9FC6;
							}
						}
					}
				}
			}
		}
		.content-shop-group{
			.info{
				padding: 20rpx;
			}
			.tab-wrap{
				margin-bottom: 20rpx;
			}
			.card-wrap{
				.card{		
					padding: 20rpx;
					border-radius: 8rpx;
					line-height: 1.6;
					.left{
						margin-right: 20rpx;	
					}
					.right{
						margin-left: 20rpx;
					}
				}
			}
		}
	}
</style>
