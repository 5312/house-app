<template>
	<view class="wrap min-height-100">
		<view class="bg-header">
			<view class="header">
				<view class="title">新盘代理楼盘查询</view>
				<view class="input-wrap">
					<u-search :show-action='false' placeholder="请输入楼盘名称" class="input" v-model="searchVal" height="80" shape="square" :clearabled="true"></u-search>
				</view>
			</view>
		</view>
		<view class="content">
			<view class="listCard">
				<view v-for="(item,index) in list" :key='index' class="card border">
					<view class="top">
						<u-tag :text="item.type" mode="dark"  bg-color='#78C340'/>
						<text>{{item.name}}</text>
					</view>
					<view class='center border-bottom'>
						<view class="c-top dis_flex">
							<text>楼盘区域</text>
							<text>报单人</text>
							<text>接单人</text>
						</view>
						<view class="c-bottom dis_flex">
							<text>大源区</text>
							<text>廖俊伟</text>
							<text>刘勇</text>
						</view>
					</view>
					<view class='time'>
						<u-icon name="clock" color="#2979ff" size="28"></u-icon>
						<text class="text">报单时间</text>
						<text>{{item.time}}</text>
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
				searchVal: '',
				list: [{
						type: '公寓',
						name: "锦汇城",
						time: '2018-02-01'
					},
					{
						type: '公寓',
						name: "锦汇城",
						time: '2018-02-01'
					},
					{
						type: '公寓',
						name: "锦汇城",
						time: '2018-02-01'
					},
					{
						type: '公寓',
						name: "锦汇城",
						time: '2018-02-01'
					}
				]
			}
		}
	}
</script>

<style lang="scss" scoped>
	.wrap {
		.bg-header{
			height: 500rpx;
			.header {
				position: fixed;
				z-index: 8;
				width: 101%;
				height:500rpx;
				background: url(../../static/image/da-top.png) 100% 100% / 100% 100% no-repeat;
				text-align: center;
				top: -2px;
				left: -1px;
				padding: 70rpx 20rpx;
				.input-wrap{
					padding: 0 20rpx;
				}
				
				.title {
					color: #fff;
					font-size: 50rpx;
					font-size: 600;
					padding: 60rpx;
					margin-bottom: 40px;
				}
			}
		}
		.content{
			height: calc(100% - 530rpx);
			padding-top: 50rpx;
		}
		.listCard {
			.card {
				width: 90%;
				margin: 0rpx auto;
				margin-bottom: 40rpx;
				background: #FFFFFF;
				border-radius: 20rpx;
				box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
				padding: 40rpx 40rpx 20rpx 40rpx;
				box-sizing: border-box;
				.top {
					text {
						margin-left: 20rpx;
					}
				}

				.center {
					padding: 20rpx 0;
					color: #131313;
					.dis_flex {
						display: flex;
						justify-content: space-between;
						padding: 20rpx;
					}
					.c-top{
						color: #4E4E4E;
					}
				}

				.time {
					padding-top: 20rpx;
					font-size: 24rpx;
					.text {
						margin: 0 20rpx;
					}
				}
			}
		}
	}
</style>
