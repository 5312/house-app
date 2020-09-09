<template>
	<view class="a-button bg-bule text-white border-radius-8 height-80 flex a-center j-center"  v-bind="$attrs" >
		<slot></slot>
	</view>
</template>

<script>
	export default{
		inheritAttrs:false,  
	}
</script>

<style scoped lang="scss">
	.a-button{
		font-size: 30rpx;
		letter-spacing: 14rpx;
		&:active{
			opacity: 0.8;
		}
	}
</style>
