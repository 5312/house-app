<template>
	<view class="income-gu page-bg min-height-100">
		<a-navbar title="工资管理" @back="$tool.uniSwitchTab({url:'/pages/home/index'})"></a-navbar>
		<view class="content">
			<view class="header bg-white flex a-center j-start flex-row">
				<view class="left flex-shrink">
					<u-avatar src='http://pic2.sc.chinaz.com/Files/pic/pic9/202002/hpic2119_s.jpg'></u-avatar>
				</view>
				<view class="right flex1">
					<view class="name">
						姓名：Ace
					</view>
					<view class="time">
						时间：2020-08
					</view>
				</view>
			</view>
			<view class="table bg-white">
				<view class="line flex a-center flex-row j-between border-bottom">
					<text>收入管理</text>
					<u-icon name="arrow-right"></u-icon>
				</view>
				<view class="flex a-center flex-row j-between box-1-wrap">
					<view class="box-1 ">
						<view class="name">
							累计收入
						</view>
						<view class="value">
							0.00
						</view>
					</view>
					<view class="box-1">
						<view class="name">
							7月收入
						</view>
						<view class="value">
							0.00
						</view>
					</view>
				</view>
			</view>
			<view class="table bg-white">
				<view class="line flex a-center flex-row j-between border-bottom">
					<text>基金管理</text>
					<u-icon name="arrow-right"></u-icon>
				</view>
				<view class="flex a-center flex-row j-between box-1-wrap flex-wrap">
					<view class="box-1">
						<view class="name">
							保底基金
						</view>
						<view class="value">
							0.00
						</view>
					</view>
					<view class="box-1 ">
						<view class="name">
							年基金
						</view>
						<view class="value">
							0.00
						</view>
					</view>
					<view class="box-1">
						<view class="name">
							孝心基金
						</view>
						<view class="value">
							0.00
						</view>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {

	}
</script>

<style scoped lang="scss">
	.income-gu {
		.content {
			.header {
				padding: 0 80rpx;
				height: 240rpx;

				.left {
					width: 110rpx;
				}

				.right {
					color: #969696;
					.name {
						margin-bottom: 6rpx;
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
							margin-bottom: 10rpx;
						}
					}
				}
			}
		}
	}
</style>
