<template>
	<view class="uncollected page-bg min-height-100">
		<a-navbar title="未收账" @back="$tool.uniSwitchTab({url:'/pages/home/index'})"></a-navbar>
		<view class="content">
			<view class="tab-wrap">
				<u-subsection active-color="#19be6b" :list="list" :current="currentIndex" @change='subsectionChange'></u-subsection>
			</view>
			<view class="card-wrap">
				<view class="card bg-white" v-for="(item,index) in cardList" :key='index'>
					<view class="title">
						{{$u.timeFormat(item.shijian, 'yyyy/mm/dd MM:ss')}}
					</view>
					<view class="box flex j-between a-center flex-row">
						<view class="left flex1">
							<view class="line">
								楼盘地址：{{item.cjname}}
							</view>
							<view class="line">
								报单类型：{{item.lupandizhi}}
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
	</view>
</template>

<script>
	export default{
		data(){
			return{
				currentIndex:0,
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
				list: [
					{
						name: '租单'
					},
					{
						name: '卖单'
					},{
						name: '新盘'
					}
				],
			}
		},
		onLoad(){
			this.init()
		},
		methods:{
			init(){
				this.$tool.uniRequest({
					url:"juece/weishouzhang/",
					method:'GET',
					params:{
						id:this.$tool.uniGetStorage('userId'),
						cjtype:String(this.currentIndex+1)
					},
					success:(res)=>{
						this.cardList=res
					}
				})	
			},
			subsectionChange(index){
				this.currentIndex=index
				this.init()
			}
		}
	}
</script>

<style scoped lang="scss">
	.uncollected{
		.content{
			padding-top: 20rpx;
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
	}
</style>
