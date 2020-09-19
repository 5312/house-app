<template>
	<view class="change-password">
		<a-navbar title="修改密码" @back="$tool.uniSwitchTab({url:'/pages/home/index'})"></a-navbar>
		<u-form class="form" :model="form" ref="uForm" v-if="form">
			<u-form-item class="bg" label-width='150' label-align='rigth' label="旧密码">
				<u-input v-model.trim="form.ymima"  password type="password" :clearable='false' placeholder='请输入旧密码' />
			</u-form-item>
			<u-form-item class="bg" label-width='150' label-align='rigth' label="新密码">
				<u-input v-model.trim="form.xmima" password :clearable='false'  type="password" placeholder='请输入新密码' />
			</u-form-item>
			<u-form-item class="bg" label-width='150' label-align='rigth' label="确认密码">
				<u-input v-model.trim="form.qrmima" password :clearable='false' type="password" placeholder='请输入确认密码' />
			</u-form-item>
		</u-form>
		<view class="btn-wrap">
			<u-button type="primary" class="btn" @click="submit">提交</u-button>
		</view>
	</view>
</template>

<script>
	export default{
		data(){
			return{
				form:{
					ymima:'',
					xmima:"",
					qrmima:""
				}
			}
		},
		methods:{
			submit(){
				if(!this.form.ymima){
					this.$tool.uniShowToast({
						title:"旧密码不能为空！",
						icon:'none'
					})
					return
				}
				if(!this.form.xmima){
					this.$tool.uniShowToast({
						title:"新密码不能为空！",
						icon:'none'
					})
					return
				}
				if(!this.form.qrmima){
					this.$tool.uniShowToast({
						title:"确认密码不能为空！",
						icon:'none'
					})
					return
				}
				if(this.form.ymima === this.form.xmima){
					this.$tool.uniShowToast({
						title:"新密码不能和旧密码相同！",
						icon:'none'
					})
					return
				}
				if(this.form.xmima === this.form.qrmima){
					this.$tool.uniShowToast({
						title:"新密码和确认密码不一致！",
						icon:'none'
					})
					return
				}
				this.$tool.uniRequest({
					url: "rbac/mima/",
					method: 'POST',
					params: this.$u.deepClone(this.form),
					success: (res) => {
						this.$tool.uniShowToast({
							title:"修改密码成功,请重新登陆！",
							icon:'none'
						})
						setTimeout(()=>{
							this.$tool.uniRemoveStorage('token')
							this.$tool.uniReLaunch({
								url:"/pages/login/index"
							})
						},10000)
					}
				})
			}
		}
	}
</script>

<style scoped lang="scss">
	.btn-wrap {
		padding: 40rpx 20rpx;
	
		.btn {
			margin-bottom: 40rpx;
		}
	}
	.form {
		border-radius: 20rpx;
		overflow: hidden;
	
		.bg {
			background: #fff;
	
			.fx {
				display: flex;
				padding: 20rpx 10rpx;
	
			}
	
			.inline {
	
				/* 	width: calc(100% - 70px); */
				/* margin: auto; */
				.input {
					padding-left: 10rpx;
					justify-content: space-around;
	
					.item {
						width: 25%;
						flex: 0 0 auto;
					}
	
					.item2 {
						width: 21%;
						margin: 0 10rpx;
						flex: 0 0 auto;
					}
				}
			}
	
			.label {
				width: 200rpx;
				text-align: right;
				margin-right: 30rpx;
			}
	
			.textarea {
				padding: 20rpx 30rpx;
			}
		}
	
	}
	
</style>
