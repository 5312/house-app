<template>
	<view class="a-alert fixed tblr flex a-center j-center" v-if="isShow">
		<view class="content border-radius-8 text-white" v-if="title">
			{{title}}
		</view>
	</view>
</template>

<script>
	export default{
		props:{
			title:{
				type:String,
				default:""
			},
			timer:{
				type:Number,
				default:2000
			},
			isShow:{
				type:Boolean,
				default:false
			}
		},
		watch:{
			isShow:{
				handler() {
				    setTimeout(()=>{
						this.$emit("update:isShow",false)
					},this.timer)
				},
				immediate: true
			}
		},		
	}
</script>

<style scoped lang="scss">
	.a-alert{
		z-index: 200;
		.content{
			padding: 20rpx 40rpx;
			background: rgba(0,0,0,0.8);
			font-size: 26rpx;
		}
	}
</style>
