<template>
	<view class="staff-location">
		 <a-navbar title="员工位置" @back="$tool.uniSwitchTab({url:'/pages/home/index'})"></a-navbar>
		 <view class="content">
		 	<view class="map-box fixed">
		 		<map style="width: 100%; height: 100%" :latitude="latitude" v-if="isMap" id='staffMap'
					:longitude="longitude" :markers="covers" :scale="scale"  :key='mapKey'>
		 		</map>
				
				<view class="search-input-box flex a-center flex-row j-between absolute">
					<u-search placeholder="内容" v-model="searchVal" :clearabled="true" :show-action="false" bg-color='#fff'></u-search>
				</view>
		 	</view>
			<view class="list">
				<view class="box flex a-center j-between border-bottom flex-row" 
					v-for="(item,index) in staffList" :key='index' @click="selectLocation(item)">
					<view class="left flex-shrink">
						<u-image width="100rpx" height='100rpx' shape='circle'  :src='item.touxiang'></u-image>
					</view>
					<view class="mid flex1">
						<view class="name bold">
							{{item.ygmingcheng}}-{{item.gangwei}}({{item.ygbianhao}})
						</view>
						<view class="time">
							获取时间：{{item.addtime}}
						</view>
					</view>
				</view>
			</view>
		 </view>
	</view>
</template>

<script>
	export default{
		data(){
			return{
				searchVal:'',
				isMap:false,
				staffMap:null,
				scale:18,
				mapKey:0,
				staffList:[],
				latitude: 39.909,
				longitude: 116.39742,
				covers: [{
					latitude: 39.909,
					longitude: 116.39742,
					iconPath: '../../../static/location.png'
				}],
			}
		},
		onLoad(){
			this.getStafflocation()
			this.getStaffList()
		},
		
		methods:{
			selectLocation(item){
				if(!item && !item.point){
					return
				}
				let lon=parseFloat(item.point.split(",")[0])
				let  lat=parseFloat(item.point.split(",")[1])
				this.isMap=false
				this.latitude=lat
				this.longitude=lon
				this.covers[0].latitude = lat
				this.covers[0].longitude = lon		
				uni.createMapContext("staffMap",this).moveToLocation({
					longitude:lon,
					latitude:lat,
					success:res=>{
						console.log('ok',res)
						console.log(lon,lat)
						console.log(item)
					},
					fail:err=>{
						console.log('err',err)
					}
				})
				this.isMap=true
			},
			getStaffList(){
				this.$tool.uniRequest({
					url: "kaoqin/weizhilist",
					method: 'GET',
					params: {
						xingming:''
					},
					success: (res) => {
						this.staffList=res
					}
				})
			},
			getStafflocation() {
				let _this = this
				uni.getLocation({
					type: 'gcj02',
					geocode: true,
					success: function(res) {
						let ress = res.longitude+ ',' + res.latitude 
						_this.longitude = res.longitude
						_this.latitude = res.latitude
						_this.covers[0].latitude = res.latitude
						_this.covers[0].longitude = res.longitude	
						_this.isMap=true					
					}
				});			
			},
		}
	}
</script>

<style scoped lang="scss">
	.staff-location{
		/deep/.search-input-box{
			background: transparent !important;
		}
		.content{
			height:100%;
			.map-box{
				height: 600rpx;
				top:100rpx;
				width:100%;
				background-color: #fff;
				overflow: hidden;
				z-index: 9;
				.search-input-box{
					width: 500rpx;
					background: white;
					bottom: 60rpx;
					left: 50%;
					margin-left: -250rpx;
					border: 1rpx solid #f4f4f5;
					border-radius: 8rpx;
					padding: 0 20rpx ;
					.search-input{
						width: 400rpx;
						background: white;
						margin-left: 20rpx;
					}
				}			
			}
			.list{
				margin-top:600rpx;
				.box{
					padding: 30rpx 0rpx;
					margin: 0 30rpx;
					.left{
						margin-right: 20rpx;
						width: 100rpx;
					}
					.mid{
						.name{
							margin-bottom: 6rpx;
							font-size: 30rpx;
						}
						.time{
							color: #70B5EC;
						}
					}
					.right{
						padding: 8rpx 20rpx;
						background: #78c340;
						font-size: 20rpx;
						margin-left: 10rpx;
					}
				}
			}
		}
	}
</style>
