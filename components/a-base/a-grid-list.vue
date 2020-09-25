<template>
	<view class="a-grid-list border-bottom">
		<view class="title bold border-bottom">
			{{title}}
		</view>
		<view class="list">
			<u-grid :col="4" :border='border'>
				<u-grid-item v-for="(item,index) in list" @click.native.stop.prevent="toPage(item)">
					<u-image width="80rpx" height="80rpx" :src="item.img" :lazy-load="true"></u-image>
					<!-- <u-image width="80rpx" height="80rpx" src="http://ming.ydeshui.com/Upload/ad/ad2.jpeg" :lazy-load="true"></u-image> -->
					
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
		onReady(){
			console.log("重新渲染了")
		},
		methods:{
			toPage(item){
				console.log(item)
				if(item.name == '开店申请' || item.name == '闭店申请'){
					uni.showToast({
						title:'稍后开放',
						icon:'none'
					})
					return
				}
				this.$tool.uniNavigateTo({
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
