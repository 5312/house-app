<template>
	<view class="shop-map padding-lr-40">
		<a-navbar title="门店导航" @back="$tool.uniSwitchTab({url:'/pages/home/index'})"></a-navbar>
		<view class="content">
			<u-collapse event-type="close"  >
				<u-collapse-item :index="index" @change="itemChange" :title="item.title" v-for="(item, index) in itemList" :key="index" class="border-bottom">
					<view class="line-list">
						<view class="line flex a-center j-start flex-row" v-for="(val,i) in item.children">
							<view class="left flex-shrink">
								<u-avatar src='http://pic2.sc.chinaz.com/Files/pic/pic9/202002/hpic2119_s.jpg'></u-avatar>
							</view>
							<view class="mid flex1">
								<view class="name bold">{{val.name}}</view>
								<view class="address">{{val.address}}</view>
							</view>
							<view class="right flex a-center j-center flex-column text-blue" @click="openLocation">
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
				itemList: [{
					title: "成华区（4）",
					open: true,
					disabled: true,
					children:[
						{
							name:"四海店",
							address:"成都市xxx区xx街xx号1楼7号成都市xxx区xx街xx号1楼7号"
						},{
							name:"四海店",
							address:"成都市xxx区xx街xx号1楼7号"
						},{
							name:"四海店",
							address:"成都市xxx区xx街xx号1楼7号"
						},{
							name:"四海店",
							address:"成都市xxx区xx街xx号1楼7号"
						},
					]
				},{
					title: "xx区（1）",
					open: true,
					disabled: true,
					children:[
						{
							name:"四海店",
							address:"成都市xxx区xx街xx号1楼7号"
						}
					]
				}],
			}
		},
		methods:{
			openLocation(){
				uni.openLocation({
				    latitude:90.0,
					longitude:50.5,
				    success: function (res) {
				        const latitude = res.latitude;
				        const longitude = res.longitude;
				        uni.openLocation({
				            latitude: latitude,
				            longitude: longitude,
				            success: function () {
				                console.log('success');
				            }
				        });
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
