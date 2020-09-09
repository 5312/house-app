<template>
	<view class="a-section height-line-80 flex a-center j-between flex-row bg-white" @click="select" :class="[isBorder && 'border-bottom']">
		<view class="left flex a-center j-start flex-row">
			<u-icon :name="item.icon" color="#c8c9cc" class="icon" size="36" v-if="item.icon"></u-icon>
			<text>{{item.name}}</text>
		</view>
		<view class="right flex a-center j-end flex-row">
			<text class="text-grey">{{item.info}}</text>
			<u-icon name="arrow-right" color="#c8c9cc" class="margin-l-10" v-if="isArrow"></u-icon>
		</view>
	</view>
</template>

<script>
	export default{
		inheritAttrs:false,
		props:{
			item:{
				type:Object,
				default:()=>({
							name:"基本信息",
							info:"完善",
							path:"/pages/home/personalInfo/baseInfo",
							icon:'man-add-fill'
						})
			},
			isBorder:{
				type:Boolean,
				default:false
			},
			isArrow:{
				type:Boolean,
				default:true
			},
		},
		methods:{
			select(){
				this.$emit("select",this.item)
			}
		}
	}
</script>

<style scoped lang="scss">
	.a-section{
		padding:0 30rpx ;
		.left{
			.icon{
				margin-right: 14rpx;
				position: relative;
				top: -2rpx;
			}
		}
	}
</style>
