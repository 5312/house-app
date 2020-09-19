<template>
	<view class="wrap">
		<a-navbar @back="$tool.uniSwitchTab({url:'/pages/mailList/index'})" backgroundColor='transparent' backIconColor="#FFFFFF"></a-navbar>
		<view class="header" v-if="userInfo">
			<image src='../../static/image/da.png' mode="aspectFill"></image>
			<view class="header-content">
				<u-avatar :src="userInfo.touxiang"></u-avatar>
				<view class="right text-white">
					<view class="name">{{userInfo.ygmingcheng}}</view>
					<view>{{userInfo.gangwei}}</view>
				</view>
			</view>
		</view>
		<view class="detail" v-if="userInfo">
			<u-form :model="userInfo" ref="uForm" label-position='top'>
				<u-form-item label="姓名">{{userInfo.ygmingcheng}}</u-form-item>
				<u-form-item label="电话">{{userInfo.dianhua}}</u-form-item>
				<u-form-item label="工号">{{userInfo.ygbianhao}}</u-form-item>
				<u-form-item label="区域店铺">{{userInfo.bumen}}</u-form-item>
			</u-form>
		</view>
		<view class="bottom flex a-center j-between flex-row">
			<text class="lint-tar"></text>
			<view class="box flex a-center j-center flex-column" v-for="(item,index) in actionList" :key='index' @click="action(item)">
				<view class="icon-box border radius flex a-center j-center">
					<u-icon :name="item.icon" color="#000" size="40"></u-icon>
				</view>
				<view class="name">
					{{item.name}}
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				userInfo: null,
				actionList: [{
					name: "打电话",
					icon: 'phone'
				}, {
					name: "发短信",
					icon: 'chat'
				}],		
			}
		},
		onLoad(options) {
			options.userId && this.getUserInfo(options.userId)
		},
		methods: {
			getUserInfo(id) {
				this.$tool.uniRequest({
					url: "rbac/geren/",
					method: 'POST',
					params: {
						id
					},
					success: (res) => {
						this.userInfo=res
						this.userInfo.touxiang= this.userInfo.touxiang || '../../static/image/header.jpg'
					}
				})
			},
			action(item) {
				if (item.icon === 'phone') {
					uni.makePhoneCall({
						phoneNumber: this.userInfo.dianhua
					});
				}

			}
		}
	}
</script>

<style lang="scss" scoped>
	page {
		height: 100%;

		.wrap {
			background: #FFFFFF;
			margin-top: -100rpx;

			.header {
				background: #FFFFFF;
				height: 600rpx;
				position: relative;

				.back {
					position: absolute;
					z-index: 9;
					width: 60rpx;
					height: 60rpx;
					padding: 20rpx;
					left: 0rpx;
					top: 10rpx;
				}

				image {
					width: 100%;
					height: 100%;
				}

				.header-content {

					position: absolute;
					bottom: 10rpx;
					width: 100%;
					padding: 20rpx;
					padding-left: 80rpx;
					display: flex;
					align-items: center;

					.name {
						margin-bottom: 10rpx;
					}

					.right {
						margin-left: 30rpx;
					}
				}
			}

			.detail {

				padding: 20rpx 20rpx;

				/deep/.u-form-item--left__content__label {
					line-height: 40rpx;
					color: #c8c9cc;
				}

				.item {
					border-bottom: 1rpx solid #cecece;
				}

				view {
					padding: 10rpx;
				}
			}

			.bottom {
				height: 10%;

				.lint-tar {
					position: absolute;
					width: 1rpx;
					height: 140rpx;
					left: 50%;
					margin-left: -1rpx;
					background: #f4f4f5;
				}

				.box {
					width: 50%;
					padding: 20px 0;
					box-sizing: border-box;

					.name {
						margin-top: 20rpx;
					}

					.icon-box {
						width: 66rpx;
						height: 66rpx;
						border: 1rpx solid #c8c9cc;
					}
				}
			}
		}
	}
</style>
