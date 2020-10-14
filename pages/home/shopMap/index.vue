<template>
	<view class="shop-map padding-lr-40">
		<a-navbar title="门店导航" @back="$tool.uniSwitchTab({url:'/pages/home/index'})"></a-navbar>	
		<view class="content" :key='collapseKey' v-if="isShowConent">
			<u-collapse event-type="close"  >
				<u-collapse-item :index="index" @change="itemChange" :title="getTitle(item)" :open='isOpen'
					v-for="(item, index) in itemList" :key="index" class="border-bottom">
					<view class="line-list">
						<view class="line flex a-center j-start flex-row" v-for="(val,i) in item.list">
							<view class="left flex-shrink">
								<u-avatar :src='val.touxiang'></u-avatar>
							</view>
							<view class="mid flex1">
								<view class="name bold">{{val.dianming}}</view>
								<view class="address">{{val.dizhi}}</view>
							</view>
							<view class="right flex a-center j-center flex-column text-blue" @click="openLocation(val)">
								<u-icon name="map-fill"></u-icon>
								<view class="text">到这去</view>
							</view>
						</view>
					</view>
				</u-collapse-item>
			</u-collapse>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				collapseKey:0,
				itemList:[],
				isOpen:false,
				isShowConent:true
			}
		},
		onLoad(){
			this.init()
		},
		methods:{
			init(){
				this.isOpen=false
			
				this.$tool.uniRequest({
					url: "rbac/daohang/",
					method: 'GET',
					success: (res) => {
						this.itemList=res		
						
						setTimeout(()=>{
							this.collapseKey++
							// this.isOpen=true
						},600)
					}
				})
			},
			getTitle(item){
				return `${item.qu}（${item.list?item.list.length:0}）`
			},
			openLocation(item){
				
				uni.openLocation({
				    latitude:item.lat,
					longitude:item.lng,
					name:item.dianming,
					address:item.dizhi,
				    success: function (res) {
				        const latitude = res.latitude;
				        const longitude = res.longitude;
						console.log(res)
				        // uni.openLocation({
				        //     latitude: latitude,
				        //     longitude: longitude,
				        //     success: function () {
				        //         console.log('success');
				        //     }
				        // });
				    }
				});
			},
			itemChange(){
				
			}
		}
	}
</script>

<style scoped lang="scss">
.shop-map{
	.content{
		.line-list{
			padding-bottom: 20rpx;
		}
		.line{
			padding: 20rpx 0;
			.left{
				margin-right: 20rpx;
				width: 100rpx;
			}
			.mid{
				.name{
					color: black;
					margin-bottom: 6rpx;
					font-size: 30rpx;
				}
			}
			.right{
				width: 100rpx;
				font-size: 30rpx;
				.text{
					margin-top: 10rpx;
					font-size: 24rpx;
				}
			}
		}
	}
}
</style>
