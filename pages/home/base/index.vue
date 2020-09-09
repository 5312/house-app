<template>
	<view class="income-gu page-bg min-height-100">
		<a-navbar :title="navbarTitle" @back="$tool.uniSwitchTab({url:'/pages/home/index'})"></a-navbar>
		<view class="content">
			<view class="header-person header-bg text-white flex a-center j-start flex-row" v-if="pageType==='person'">
				<view class="left flex-shrink">
					<u-avatar src='http://pic2.sc.chinaz.com/Files/pic/pic9/202002/hpic2119_s.jpg' size="120"></u-avatar>
				</view>
				<view class="right flex1">
					<view class="name">
						姓名：Ace
					</view>
					<view class="time">
						当前职务：实习置业顾问
					</view>
					<view class="time">
						当前店铺：橡树2号店01组
					</view>
				</view>
			</view>
			<view class="header-shop-group header-bg text-white flex a-center j-start flex-row" v-if="pageType==='shopGroup' || pageType==='shop'">
				<view class="right flex1">	
					<view class="time">
						所属店铺：橡树2号店01组
					</view>
				</view>
			</view>
			<view class="table bg-white">
				<view class="line flex a-center flex-row j-between border-bottom" @click="toOther('sameMonth')">
					<text>当月业绩（2020-08）</text>
					<u-icon name="arrow-right" class="text-grey"></u-icon>
				</view>
				<view class="flex a-center flex-row j-between box-1-wrap">
					<view class="box-1 ">
						<view class="name">
							分成业绩
						</view>
						<view class="value">
							0.00
						</view>
					</view>
					<view class="box-1">
						<view class="name">
							已收业绩
						</view>
						<view class="value">
							0.00
						</view>
					</view>
				</view>
			</view>
			<view class="table bg-white">
				<view class="line flex a-center flex-row j-between border-bottom" @click="toOther('total')">
					<text>总业绩</text>
					<u-icon name="arrow-right" class="text-grey"></u-icon>
				</view>
				<view class="flex a-center flex-row j-between box-1-wrap flex-wrap">
					<view class="box-1">
						<view class="name">
							分成业绩
						</view>
						<view class="value">
							0.00
						</view>
					</view>
					<view class="box-1">
						<view class="name">
							已收业绩
						</view>
						<view class="value">
							0.00
						</view>
					</view>
				</view>
			</view>
			<view class="table bg-white">
				<view class="line flex a-center flex-row j-between border-bottom" @click="toOther('rank')">
					<text>业绩排名</text>
					<u-icon name="arrow-right" class="text-grey"></u-icon>
				</view>
				<view class="flex a-center flex-row j-between box-1-wrap flex-wrap" v-if="rankList.length">
					<view class="box-1 flex a-center j-center flex-column" v-for="(item,index) in rankList" :key='index'>
						<view class="value">
							{{item.value}}
						</view>
						<view class="name">
							{{item.name}}
						</view>
					</view>
					
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data(){
			return{
				pageType:"person",
				rankList:[],
				navbarTitle:'',
				rankShopList:[
					{
						name:"当月门店排名",
						value:"0"
					},{
						name:"门店总排名",
						value:"0"
					},
				],
				rankShopGroupList:[
					{
						name:"当月门店内排名",
						value:"0"
					},{
						name:"当月店组总排名",
						value:"0"
					},{
						name:"门店总业绩排名",
						value:"0"
					},{
						name:"店组总业绩排名",
						value:"0"
					}
				],
				rankPersonList:[
					{
						name:"当前店铺内排名",
						value:"0"
					},{
						name:"当月个人总排名",
						value:"0"
					},{
						name:"店铺内业绩总排名",
						value:"0"
					},{
						name:"个人总业绩排名",
						value:"0"
					}
				]
			}
		},
		onLoad(options){
			this.pageType=options.type
			this.init()
		},
		methods:{
			init(){
				switch(this.pageType){
					case "person":
						this.rankList=this.rankPersonList
						this.navbarTitle="个人业绩"
						break
					case "shopGroup":
						this.rankList=this.rankShopGroupList
						this.navbarTitle="店组业绩"
						break
					case "shop":
						this.rankList=this.rankShopList
						this.navbarTitle="店铺业绩"
						break
				}
				
			},
			toOther(type){
				let url=''
				switch(type){
					case 'sameMonth':
						url=`/pages/home/base/sameMonth?type=${this.pageType}`
						break
					case 'total':
						url=`/pages/home/base/total?type=${this.pageType}`
						break
					case 'rank':
						url=`/pages/home/base/rank?type=${this.pageType}`
						break
					default :
						break
				}
				this.$tool.uniNavigateTo({url})
			}
		}
	}
</script>

<style scoped lang="scss">
	.income-gu {
		.content {
			.header-person {
				padding: 0 80rpx;
				height: 240rpx;

				.left {
					width: 140rpx;

				}

				.right {
					.name {
						margin-bottom: 20rpx;
					}
				}
			}
			.header-shop-group {
				padding: 0 40rpx;
				height: 120rpx;
			
				.left {
					width: 140rpx;
			
				}
			
				.right {
					.name {
						margin-bottom: 20rpx;
					}
				}
			}
			.table {
				margin: 40rpx 0;
				padding: 0 40rpx;

				.line {
					height: 80rpx;
					line-height: 80rpx;
				}

				.box-1-wrap {
					color: #8EC1E0;
					min-height: 120rpx;
					padding: 20rpx 0;

					.box-1 {
						width: 50%;
						margin-bottom: 20rpx;

						.name {
							margin-top: 10rpx;
						}
					}
				}
			}
		}
	}
</style>
