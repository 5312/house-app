<template>
	<view class="staff-location">
		 <a-navbar title="员工位置" @back="$tool.uniSwitchTab({url:'/pages/home/index'})"></a-navbar>
		 <view class="content">
		 	<view class="map-box relative">
		 		<map style="width: 100%; height: 100%" :latitude="latitude" :longitude="longitude" :markers="covers" :scale="18">
		 		</map>
				
				<view class="search-input-box flex a-center flex-row j-between absolute">
					<u-search placeholder="内容" v-model="searchVal" :clearabled="true" :show-action="false" bg-color='#fff'></u-search>
				</view>
		 	</view>
			<view class="list">
				<view class="box flex a-center j-between border-bottom flex-row" v-for="(item,index) in staffList" :key='index'>
					<view class="left flex-shrink">
						<u-image width="100rpx" height='100rpx' shape='circle'  src='http://pic2.sc.chinaz.com/Files/pic/pic9/202002/hpic2119_s.jpg'></u-image>
					</view>
					<view class="mid flex1">
						<view class="name bold">
							{{item.name}}-{{item.jum}}({{item.account}})
						</view>
						<view class="time">
							获取时间：{{$u.timeFormat(item.time, 'yyyy-mm-dd hh:MM:ss')}}
						</view>
					</view>
					<view class="right flex-shrink flex a-center flex-row j-center text-white border-radius-8">
						<u-icon name="plus"></u-icon>
						<text>关注</text>
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
				staffList:[
					{
						img:"",
						name:"王小二",
						jum:"合伙人",
						account:"010203",
						time:new Date().getTime()+'',
						isFollow:false
					},{
						img:"",
						name:"王小二",
						jum:"合伙人",
						account:"010203",
						time:new Date().getTime()+'',
						isFollow:false
					}
				],
				latitude: 39.909,
				longitude: 116.39742,
				covers: [{
					latitude: 39.909,
					longitude: 116.39742,
					iconPath: '../../../static/location.png'
				}],
			}
		}
	}
</script>

<style scoped lang="scss">
	.staff-location{
		/deep/.search-input-box{
			background: transparent !important;
		}
		.content{
			
			.map-box{
				height: 600rpx;
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
