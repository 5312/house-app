<template>
	<view class="same-month page-bg min-height-100">
		<a-navbar title="当月业绩" @back="back"></a-navbar>
		<view class="header header-bg text-white">
			<view class="time">
				2020-08
			</view>
			<view class="header-info">
				<view class="text-center">当月分成总业绩</view>
				<view class="text-center count">0</view>
				<view class="text-center">已收：0</view>
			</view>
		</view>
		<view class="content-person" v-if="pageType==='person' || isPersonDetail">
			<view class="info bg-white">
				共有{{totalOrder}}个相关报单
			</view>
			<view class="tab-wrap">
				<u-subsection :list="list" :current="1"></u-subsection>
			</view>
			<view class="card-wrap">
				<view class="card bg-white" v-for="(item,index) in cardList" :key='index'>
					<view class="title">
						{{$u.timeFormat(item.time, 'yyyy/mm/dd MM:ss')}}
					</view>
					<view class="box flex j-between a-center flex-row">
						<view class="left flex1">
							<view class="line">
								楼盘地址：{{item.address}}
							</view>
							<view class="line">
								报单类型：{{item.type}}
							</view>
							<view class="line">
								分成比例：{{item.num}}
							</view>
							<view class="line">
								业绩所属店铺：{{item.shop}}
							</view>
						</view>
						<view class="right flex-shrink">
							<view class="value">
								{{item.total}}
							</view>
							<view>
								已收：{{item.price}}
							</view>
						</view>
					</view>
				</view>
			</view>
		
		</view>
		<view class="content-shop-group" v-if="(pageType==='shopGroup' || pageType==='shop') && !isPersonDetail">
			<view class="card-wrap">
				<view class="card bg-white border-bottom flex j-between a-center flex-row" v-for="(item,index) in listArr" :key='index'
					@click="toPage(item)">
					<view class="left flex-shrink">	
						<u-avatar :src="item.img"></u-avatar>
					</view>
					<view class="mid flex1">
						<view class="name bold">
							{{item.name}} <text v-if="item.account">（{{item.account}}）</text>
						</view>
						<view class="line flex a-center j-between flex-row">
							<text>月总业绩</text>
							<text class="text-blue">{{item.value1}}</text>
							<text>已收业绩</text>
							<text class="text-blue">{{item.value2}}</text>
						</view>
					</view>
					<view class="right flex-shrink">
						<u-icon name="arrow-right"></u-icon>
					</view>
				</view>
			</view>
		</view>
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				totalOrder:1,
				pageType:"",
				isPersonDetail:false,
				listArr:[],
				shopGroupList:[
					{
						img:'http://pic2.sc.chinaz.com/Files/pic/pic9/202002/hpic2119_s.jpg',
						name:"小二",
						account:"010206",
						value1:'14560.0',
						value2:'14560.0',
					},{
						img:'http://pic2.sc.chinaz.com/Files/pic/pic9/202002/hpic2119_s.jpg',
						name:"小二",
						account:"010206",
						value1:'14560.0',
						value2:'14560.0',
					}
				],
				shopList:[
					{
						img:'http://pic2.sc.chinaz.com/Files/pic/pic9/202002/hpic2119_s.jpg',
						name:"橡树2号店D组",
						value1:'14560.0',
						value2:'14560.0',
					},{
						img:'http://pic2.sc.chinaz.com/Files/pic/pic9/202002/hpic2119_s.jpg',
						name:"橡树2号店D组",
						value1:'14560.0',
						value2:'14560.0',
					}
				],
				cardList:[
					{
						time:new Date().getTime(),
						address:"新宇华悦3-1-1805",
						type:"新盘",
						num:8,
						shop:"新宇华悦05店组",
						total:"1760.09",
						price:'0'
					},{
						time:new Date().getTime(),
						address:"新宇华悦3-1-1805",
						type:"新盘",
						num:8,
						shop:"新宇华悦05店组",
						total:"1760.09",
						price:'0'
					}
				],
				list: [{
						name: '全部'
					},
					{
						name: '租单'
					},
					{
						name: '卖单'
					},{
						name: '新盘'
					}
				],
				current: 1
			}
		},
		onLoad(options){
			this.pageType=options.type
			this.init()
		},
		methods:{
			init(){
				switch(this.pageType){
					case "shop":
						this.listArr=this.shopList
						break
					case "shopGroup":
						this.listArr=this.shopGroupList
						break
				}
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
			}
		}
	}
</script>

<style scoped lang="scss">
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
