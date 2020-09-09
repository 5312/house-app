<template>
	<view class="a-grid-list border-bottom">
		<view class="title bold border-bottom">
			{{title}}
		</view>
		<view class="list">
			<u-grid :col="4" :border='border'>
				<u-grid-item v-for="(item,index) in list" @click.native.stop.prevent="toPage(item)">
					<u-image width="80rpx" height="80rpx" :src="item.img"></u-image>
					<view class="grid-text name">{{item.name}}</view>
				</u-grid-item>					
			</u-grid>
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
			list:{
				type:Array,
				default:()=>([])
			},
			border:{
				type:Boolean,
				default:false
			}
		},
		methods:{
			toPage(item){
				this.$tool.uniRedirectTo({
					url:item.path
				})
			}
		}
	}
</script>

<style scoped lang="scss">
	.a-grid-list{
		.title{
			height: 80rpx;
			line-height: 80rpx;
			font-size: 30rpx;
			padding: 0 30rpx;
		}
		.list{
			.name{
				margin-top: 14rpx;
				font-size: 24rpx;
			}
		}
	}
</style>
