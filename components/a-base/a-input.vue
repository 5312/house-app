<template>
	<view class="a-input bg-white flex a-center j-start flex-row border-radius-8 height-80">
		<image :src="iconUrl" mode="aspectFit" class="icon-img"></image>
		<input :value="value" @input="input" v-bind="$attrs" class="input"/>
	</view>
</template>

<script>
	export default{
		inheritAttrs:false, 
		data(){
			return{
				userIcon:require("../../static/image/user-icon.png"),
				passwordIcon:require('../../static/image/password-icon.png')
			}
		},
		props:{
			value:{
				type:String,
				default:''
			},
			iconType:{
				type:String,
				default:'user'
			},
		},
		computed:{
			iconUrl(){
				let url=''
				if(this.iconType==='user'){
					url=this.userIcon
				}else if(this.iconType==='password'){
					url=this.passwordIcon
				}
				return url
			}
		},
		methods:{
			input(e){
				this.$emit("input",e.target.value)
			}
		}
	}
</script>

<style scoped lang="scss">
	.a-input{
		margin-bottom: 30rpx;	
		padding: 0 20rpx;
		.input{
			margin-left: 16rpx;
			font-size: 28rpx;
		}
		.icon-img{
			width: 34rpx;
		}
	}
</style>
