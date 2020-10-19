<template>
	<view class="clock-in">
		<a-navbar title="考勤打卡" @back="$tool.uniSwitchTab({url:'/pages/home/index'})"></a-navbar>
		<u-tabs :list="list" active-color='#07c160' :is-scroll="false" :current="current" @change="change" height='100'></u-tabs>
		<view class="content">
			<view class="content-1" v-if="current!==2">
				<view class="map-box">
					<map id='myMap' style="width: 100%; height: 300px;" :latitude="latitude" :longitude="longitude" :markers="covers"
					 :scale="18" @tap="tapMap" v-show="isMap">
					</map>
				</view>
				<view class="info-box">
					<view class="line">上班时间：{{workTimeRocord && workTimeRocord.list && workTimeRocord.list.length 
						? workTimeRocord.list[0].addtime:'未打卡'}}</view>
						<view class="line blue">{{workTimeRocord && workTimeRocord.list && workTimeRocord.list.length
							? workTimeRocord.list[0].ress:''}}</view>
					<view class="line">下班时间：{{workTimeRocord && workTimeRocord.list && workTimeRocord.list.length 
					    &&	workTimeRocord.list[1] ?workTimeRocord.list[1].addtime:'未打卡'}}</view>
						<view class="line blue">{{workTimeRocord && workTimeRocord.list && workTimeRocord.list.length
						    &&	workTimeRocord.list[1] ?workTimeRocord.list[1].ress:''}}</view>
					<view class="line" v-if="userInfo">当前职务：{{userInfo.gangwei || ''}}</view>
				</view>
				<!-- 按钮 -->
				<view class="count-box flex a-center j-center">
					<view class="count-bg radius flex  a-center j-center" @click="punchClock" :class="[this.workTimeRocord && this.workTimeRocord.list.length>=2 && 'bg-grey']">
						<view class="count-text" v-if="!this.workTimeRocord || this.workTimeRocord.list.length<2">打卡</view>
						<text>{{currentTimeVal}}</text>
					</view>
				</view>
				<view class="address-info red" v-if="workTimeRocord">
					{{ workTimeRocord.dizhi.length>0?workTimeRocord.dizhi:''}}
				</view>
			</view>
			<view class="content-3" v-if="current===2">
				<view class="record-box">
					<a-calendar ref='calendarDom' @getNowDate='getNowDate' :dateStatusList='dateStatusList'></a-calendar>
				</view>
				<view class="record-time">
					<view class="line border-bottom border-top">上班：{{ am }}</view>
					<view class="line border-bottom">下班：{{ pm }}</view>
					<!-- <view class="line-info">提示：未审核考勤将不计算工资，请联系上一级及时审核！</view> -->
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import api from '@/utils/api/info.js';
	export default {
		data() {
			return {
				isMap: false,
				isShowCalendar: true,
				currentCalender: null,
				workTimeRocord: null,
				dateStatusList: [],
				currentWorkTime: null,
				userInfo: null,
				currentTimeVal: null,
				list: [{
					name: '正常打卡'
				}, {
					name: '外勤打卡'
				}, {
					name: '打卡记录'
				}],
				current: 0,
				latitude: '',
				longitude: '',
				covers: [{
					latitude: '',
					longitude: '',
					iconPath: '../../../static/location.png'
				}],
				timestamp: 86400,
				address: ''
			}
		},
		onLoad() {
			this.userInfo = this.$tool.uniGetStorage('userInfo')
			this.getcurrentTimeVal()
			this.getClockInfo(() => {
				this.getMapInfo()
			})

		},
		computed:{
			pm:function(){
				if(!this.currentWorkTime) return '无记录'
				if(this.currentWorkTime[1]){
					if(!this.currentWorkTime[1].pm) return '无记录'
					return this.currentWorkTime[1].pm.addtime;
				}
				return '无记录'
			},
			am:function(){
				if(!this.currentWorkTime) return '无记录'
				if(this.currentWorkTime[0]){
					if(!this.currentWorkTime[0].am) return '无记录'
					return this.currentWorkTime[0].am.addtime;
				}
				return '无记录'
			}
		},
		methods: {
			tapMap(e) {
				let that = this
				let maps = uni.createMapContext("myMap", this).$getAppMap();
				maps.onclick = function(point) {
					if (that.current == 1) {
						that.longitude = point.longitude
						that.latitude = point.latitude
						that.covers[0].latitude = point.latitude
						that.covers[0].longitude = point.longitude
					}
				}
			},
			getNowDate(obj) {
				this.currentCalender = obj
				this.currentCalender && this.getRocord(obj.isClick)
			},
			getcurrentTimeVal() {
				setInterval(() => {
					this.currentTimeVal = this.$u.timeFormat(String(new Date().getTime()), 'hh:MM:ss');
				}, 1000)
			},
			punchClock() {
				if (this.workTimeRocord && this.workTimeRocord.list.length >= 2) {
					return
				}
				let _this = this
				let index = this.current
				if (index !== 2) {
					index += 1
				} else {
					return
				} 
				let ress = this.longitude + ',' + this.latitude
				let params = {
					type: index, //打卡类型1正常2外出
					location: ress,
					ress: _this.workTimeRocord && _this.workTimeRocord.dizhi || _this.address,
					remark: ''
				}
				console.log(params)
				api.qianDaoPost(params).then( res => {
					_this.getMapInfo()
				})
			},
			getClockInfo(callback = null) {
				let _this = this
				uni.getLocation({
					type: 'gcj02',
					geocode: true,
					success: function(res) {
						console.log('gcj02',res)
						_this.longitude = res.longitude
						_this.latitude = res.latitude
						_this.covers[0].latitude = res.latitude
						_this.covers[0].longitude = res.longitude
						_this.address = res.address.province + res.address.city + res.address.district + res.address.street + res.address
							.streetNum + res.address.poiName + res.address.cityCode
						_this.isMap = true
						callback && callback()
					}
				})
			},
			getMapInfo() {
				let ress = this.longitude + ',' + this.latitude
				this.$tool.uniRequest({
					url: "kaoqin/qiandao",
					method: 'GET',
					params: {
						type: this.current + 1, //打卡类型1正常2外出
						location: ress,
						ress: this.workTimeRocord && this.workTimeRocord.dizhi || this.address,
						remark: ''
					},
					success: (res) => {
						console.log(res)
						this.workTimeRocord = res
					}
				})
			},
			change(index) {
				this.current = index;
				if (index !== 2) {
					if (index === 0) {
						this.getClockInfo(() => {
							this.getMapInfo()
						})
					} else {
						this.getMapInfo()
					}

				}
			},
			getRocord(isClick = false) {
				if (!isClick) {
					this.getRocordMonth()
				}
				this.getRocordMonth(true)
			},
			getRocordMonth(isDay = false) {
				this.$tool.uniRequest({
					url: "kaoqin/qiandaolist",
					method: 'GET',
					params: {
						yuefen: `${this.currentCalender.year}-${this.currentCalender.month}`,
						ri: isDay ? this.currentCalender.day : ""
					},
					success: (res) => {
						if (isDay) {
							this.currentWorkTime = res || null
						} else {
							this.dateStatusList = res || []
						}
					}
				})
			},


		}
	}
</script>

<style scoped lang="scss">
	.clock-in {
		.bg-grey {
			background: grey !important;
			box-shadow: 1px 1px 6px 8px #797777 !important;
		}

		.address-info {
			color: #cccccc;
			font-size: 24rpx;
			margin: 20rpx 0 60rpx 0;
			text-align: center;
			width: 80%;
			margin-left: 10%;
			margin-top:40rpx;
		}
		.blue{
			color: #479AFE;
		}
		.red{
			color:red;
		}
		.content {
			.content-1 {
				.info-box {
					padding: 30rpx 40rpx;

					.line {
						min-height: 60rpx;
						line-height: 60rpx;
					}
				}

				.count-box {
					margin: 40rpx 0;
					.count-bg {
						box-shadow: 1px 1px 6px 11px #6ce8aa;
						width: 200rpx;
						height: 200rpx;
						background: #19be6b;
						color: #FFFFFF;
						text-align: center;
						line-height: 50rpx;
						display: flex;
						flex-direction: column;

						.count-text {
							line-height: 80rpx;
							// margin-top: 30rpx;
							font-size: 40rpx;
							font-weight: 600;
						}
					}
				}
			}

			.content-3 {
				.record-time {
					.line {
						height: 80rpx;
						line-height: 80rpx;
						padding: 0 40rpx;
					}

					.line-info {
						line-height: 1.6;
						min-height: 80rpx;
						padding: 20rpx 40rpx;
						color: red;
					}
				}
			}
		}
	}
</style>
