<template>
	<view class="growth-tree">
		<a-navbar title="成长树" @back="$tool.uniSwitchTab({url:'/pages/home/index'})"></a-navbar>
		<view class="header header-bg flex a-center j-center flex-column relative">
			<view class="header-info bg-white absolute border border-radius-8">
				<view class="name">
					Ace(No.070605)
				</view>
				<view class="info-wrap flex a-center j-start flex-row flex-wrap">
					<view class="line flex-shrink" v-for="(item,index) in infoList" :key='index'>
						<text>{{item.prop}}</text>：
						<text>{{item.name}}</text>
					</view>
				</view>
			</view>
		</view>
		<view class="content">
			<u-time-line>
				<u-time-line-item v-for="(item,index) in list">
					<template v-slot:content>
						<view class="border box-temp border-radius-8" :class="[index==3 && 'header-bg text-white']">
							<view class="u-order-title">{{index+1}}.{{item.title}}</view>
							<view class="u-order-desc">{{item.text}}</view>					
						</view>
					</template>
				</u-time-line-item>				
			</u-time-line>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				list:[
					{
						title:"新人入职",
						text:"欢迎来到大唐，大唐会陪你成长！"
					},{
						title:"新人入职",
						text:"欢迎来到大唐，大唐会陪你成长！"
					},{
						title:"新人入职",
						text:"欢迎来到大唐，大唐会陪你成长！"
					},{
						title:"新人入职",
						text:"欢迎来到大唐，大唐会陪你成长！"
					},
				],
				infoList: [{
					name: "实现置业顾问",
					prop: "当前职务"
				}, {
					name: "111111",
					prop: "联系方式"
				}, {
					name: "2020/05/05",
					prop: "入职时间"
				}, {
					name: "未签订",
					prop: "当前阶段"
				}]
			}
		}
	}
</script>

<style scoped lang="scss">
	.growth-tree {
		.header {
			height: 300rpx;
			.header-info {
				height: 260rpx;
				left: 50%;
				width: 680rpx;
				margin-left: -340rpx;
				top: 100rpx;
				padding: 40rpx;
				box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
				.name{
					color: #90C5E5;
				}
				.info-wrap {
					margin-top: 24rpx;

					.line {
						width: 50%;
						font-size: 25rpx;
						line-height: 2;
						margin-bottom: 10rpx;
					}
				}
			}
		}
		.content{
			padding: 40rpx 80rpx;
			margin-top: 80rpx;
			.box-temp{
				padding: 20rpx;
				font-size: 24rpx;
			}
		}
	}
</style>
