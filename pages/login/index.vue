<template>
	<view class="wrap fixed tblr flex a-center j-center">
		<view class="conter">
			<image class="logo" src="../../static/image/logo.png" mode=""></image>
			<view class="inp">
				<view class="input">
					<u-icon class="icon" name="account"></u-icon>
					<u-input  v-model.trim="bianhao" :type="type1" :border="border1" />
				</view>
				<view class="input">
					<u-icon class="icon" name="lock-fill"></u-icon>
					<u-input  v-model.trim="password" :type="type2" :border="border2" />
				</view>
				<u-button class='btn' type="success"  @click.native='login' >登录</u-button>
			</view>
			<view class="bottom">
				<text>忘记密码？</text>
			</view>
		</view>
		<view class="version">
			<text>OA办公系统</text>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				bianhao: '10007',
				type1: 'text',
				border1: true,
				password: '222222',
				type2: 'password',
				border2: true
			}
		},
		methods: {
			login(){
				if(!this.password){
					this.uniShowToast({
						title: "请输入用户名",
						icon: "none"
					})
					return
				}
				this.$tool.uniRequest({
					url:"login/",
					method:'POST',
					isLogin:true,
					params:{
						bianhao:this.bianhao,
						password:this.password
					},
					success:(res)=>{
						this.$tool.uniSetStorage('token',res.Accept)
						this.$tool.uniSetStorage('userId',res.id)
						this.$tool.uniSetStorage('userInfo',res)
						this.$tool.uniReLaunch({
							url:"/pages/home/index"
						})
					}
				})			
			},
			
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
			.inp{
				width:80%;
				margin:auto;
				.input{
					position:relative;
					.u-input{
						padding:0 20rpx 0 50rpx!important;
					}
					.icon{
						position: absolute;
						top:25rpx;
						left:10rpx;
					}
					margin-top:60rpx;
				}
				.btn{
					margin-top:50rpx;
				}
			}
			.bottom{
				text-align: right;
				padding:50rpx;
			}
		}
		.version{
			position: absolute;
			bottom:100rpx;
			width:100%;
			margin:auto;
			text-align: center;
			text{
				background: rgba(136, 136, 136, 0.68);
				padding:10rpx 20rpx;
				border-radius:30rpx;
				color:#fff;
				
			}
		}
	}
</style>
