<template>
	<view class="wrap fixed tblr flex a-center j-center">
		<u-modal v-model="show" z-index="999" @confirm="confirm" confirm-text='升级' :show-cancel-button="colse" title="版本更新" :content='content' ref="uModal" :async-close="true"></u-modal>
		<view class="conter">
			<image class="logo" src="../../static/image/logo.png" mode=""></image>
			<view class="inp">
				<view class="input">
					<u-icon class="icon" name="account"></u-icon>
					<u-input v-model.trim="bianhao" :type="type1" :border="border1" />
				</view>
				<view class="input">
					<u-icon class="icon" name="lock-fill"></u-icon>
					<u-input v-model.trim="password" :type="type2" :border="border2" />
				</view>
				<u-button class='btn' type="success" @click.native='login'>登录</u-button>
			</view>
		</view>
		<view class="version">
			<text>版本:v{{versioninfo}}</text>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				colse:true,
				show: false,
				content: null,
				versioninfo: '1.0.0',
				url: null,
				bianhao: '',
				type1: 'text',
				border1: true,
				password: '',
				type2: 'password',
				border2: true
			}
		},
		onLoad() {
			let _this = this;
			//#ifdef APP-PLUS  
			plus.runtime.getProperty(plus.runtime.appid, function(widgetInfo) {
				_this.versioninfo = widgetInfo.version;
				let header = {
					'Content-Type': 'application/x-www-form-urlencoded',
				}
				_this.$tool.uniShowLoading({})
				console.log(widgetInfo.versionCode)
				uni.request({
					url: 'http://ming.ydeshui.com/api/version/',
					method: 'GET',
					withCredentials: false,
					data: {
						version: widgetInfo.versionCode
					},
					header: header,
					success: function(result) {
						_this.$tool.uniHideLoading({})
						console.log('ver', result)
						if (result.data.code == 0) {
							_this.show = true;
							_this.content = result.data.msg;
							_this.url = result.data.data[0].url;
						}
					},
					fail: (err) => {
						_this.$tool.uniShowToast({
							title: "请求失败，请重试",
							icon: "none"
						})
					},
					complete: () => {
						_this.$tool.uniHideLoading({})
						//complete ? complete() : false
					}
				})
			})
			//#endif  
			this.cookieLogin();
		},
		methods: {
			confirm() {
				let downUrl = this.url;
				let _this = this;
				console.log(downUrl)
				var dtask = plus.downloader.createDownload(downUrl, {}, function(d, status) {
					// 下载完成
					_this.show = false;
					if (status == 200) {
						plus.runtime.install(plus.io.convertLocalFileSystemURL(d.filename), {}, {}, function(error) {
							uni.showToast({
								title: '安装失败',
								duration: 1500
							});
						})
					} else {
						uni.showToast({
							title: '更新失败',
							duration: 1500
						});
					}
				});
				dtask.start();
			},
			login() {
				if (!this.password) {
					this.uniShowToast({
						title: "请输入用户名",
						icon: "none"
					})
					return
				}
				this.$tool.uniRequest({
					url: "login/",
					method: 'POST',
					isLogin: true,
					params: {
						bianhao: this.bianhao,
						password: this.password
					},
					success: (res) => {
						this.$tool.uniSetStorage('token', res.Accept)
						this.$tool.uniSetStorage('userId', res.id)
						this.$tool.uniSetStorage('userInfo', res)
						this.$tool.uniSetStorage('ygbianhao',res.ygbianhao)
						  
						if(res != 0 ){
							this.$tool.uniReLaunch({
								url: "/pages/home/index"
							})
						}
					}
				})
			},
			cookieLogin(){
				const token = this.$tool.uniGetStorage('token');
				const userId = this.$tool.uniGetStorage('userId');
				const userInfo = this.$tool.uniGetStorage('userInfo');
				this.bianhao = this.$tool.uniGetStorage('ygbianhao');
				if(token && userId && userInfo){
					this.$tool.uniReLaunch({
						url: "/pages/home/index"
					})
				}
			}
		}
	}
</script>

<style lang="scss" scoped>
	page {
		height: 100%;
	}

	.wrap {
		padding: 180rpx 0rpx 30rpx;
		background: url(../../static/image/login-bg.png) 100% 100% /100% 100% no-repeat;

		.conter {
			width: 80%;
			height: 800rpx;
			background: #fefefe;
			margin-top: -80px;
			border-radius: 20rpx;

			.logo {
				display: block;
				width: 250rpx;
				height: 250rpx;
				margin: auto;
			}

			.inp {
				width: 80%;
				margin: auto;

				.input {
					position: relative;

					.u-input {
						padding: 0 20rpx 0 50rpx !important;
					}

					.icon {
						position: absolute;
						top: 25rpx;
						left: 10rpx;
					}

					margin-top:60rpx;
				}

				.btn {
					margin-top: 50rpx;
				}
			}

			.bottom {
				text-align: right;
				padding: 50rpx;
			}
		}

		.version {
			position: absolute;
			bottom: 100rpx;
			width: 100%;
			margin: auto;
			text-align: center;

			text {
				background: rgba(136, 136, 136, 0.68);
				padding: 10rpx 20rpx;
				border-radius: 30rpx;
				color: #fff;

			}
		}
	}
</style>
