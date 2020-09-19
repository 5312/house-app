<template>
	<view class="a-calendar">
		<view class="t-header flex a-center j-center flex-row">
			<view class="prev">
				<u-icon name="arrow-left-double" @click="actionHandle('prevYear')"></u-icon>
				<u-icon name="arrow-left" class="margin-l-40" @click="actionHandle('prevMonth')"></u-icon>
			</view>
			<view class="title">
				{{nowYear}}年{{nowMonth}}月
			</view>
			<view class="next">
				<u-icon name="arrow-right" @click="actionHandle('nextMonth')"></u-icon>
				<u-icon name="arrow-right-double" class="margin-l-40" @click="actionHandle('nextYear')"></u-icon>
			</view>
		</view>
		<!-- <view class="t-info flex a-center j-between flex-row text-white">
			<view class="info-box flex1 flex a-center j-center flex-column" v-for="(item,index) in infoList" :key='index'>
				<view class="name">{{item.name}}</view>
				<view class="value">{{item.value}}</view>
			</view>
		</view> -->
		<view class="t-body">
			<view class="t-body-header flex a-center j-between flex-row">
				<text v-for="(item,index) in weekList" :key='index' class="inline-block week-cell cell flex1">{{item}}</text>
			</view>
			<view class="t-body-main" :key='tobdyKey'>
				<u-row>
					<u-col :span="12/7" v-for="i in weekDay">
						<text class="inline-block day-cell day-cell-disabled cell flex1 w100">{{preMonthSize - weekDay + i}}</text>
					</u-col>
					<u-col :span="12/7" v-for="item in  monthList[nowMonth - 1]" @click="selectTime(item)">
						<text class="inline-block day-cell cell flex1 w100" 
							:class="[todayYear===nowYear && todayMonth===nowMonth && clickActiveIndex=== item && 'day-cell-active',todayYear===nowYear && todayMonth===nowMonth && todayDay=== item && 'day-cell-today']">
							{{item}}
							<text :style="[getTriangleTopClass(item)]" class="absolute inline-block triangle-right"></text>
							<text :style="[getTriangleBottomClass(item)]" class="absolute inline-block triangle-right"></text>
						</text>
					</u-col>
					<u-col :span="12/7" v-for="j in lastWeekDay">
						<text class="inline-block day-cell day-cell-disabled cell flex1 w100">{{j}}</text>
					</u-col>
				</u-row>
			</view>
		</view>
		<view class="t-footer flex a-center j-between flex-row">
			<!-- <view class="t-footer-box flex a-center j-center flex-row" v-for="(item,index) in footerList" :key='index'>
				<text class="color inline-block" :style="{background:item.bg}"></text>
				<text class="label">{{item.name}}</text>
			</view> -->
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				timestamp: null,
				nowYear: 1991,
				nowMonth: 2,
				nowDay: 1,
				todayYear: 1991,
				todayMonth: 2,
				todayDay: 1,
				weekDay: 1,
				lastWeekDay: 1,
				clickActiveIndex: 1,
				weekList: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
				monthList: [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31],
				footerList: [{
					bg: "#EC7830",
					name: "迟,早",
					status: 1,
				}, {
					bg: "#8B24A1",
					status: 2,
					name: "缺勤"
				}, {
					bg: "#5CAAE1",
					status: 3,
					name: "事假"
				}, {
					bg: "#78C9CB",
					status: 4,
					name: "其他"
				}, {
					bg: "#ED7276",
					status: 5,
					name: "旷工"
				}, {
					bg: "#52B243",
					status: 6,
					name: "出,培"
				}, ],
				infoList: [{
					name: "迟到",
					value: "0.0"
				}, {
					name: "休假",
					value: "0.0"
				}, {
					name: "缺勤",
					value: "0.0"
				}, {
					name: "事假",
					value: "0.0"
				}, {
					name: "旷工",
					value: "0.0"
				}, {
					name: "其他",
					value: "0.0"
				}]
			}
		},
		created() {
			this.init()
		},
		props:{
			dateStatusList:{
				type:Array,
				default:()=>([])
			}
		},
		computed: {
			preMonthSize() { //上一个月有多少天
				return this.nowMonth - 1 === 0 ? 31 : this.monthList[this.nowMonth - 2]
			},
			tobdyKey(){
				console.log(this.dateStatusList)
				return this.dateStatusList.length
			},
		},
		methods: {
			init() {
				this.timestamp = new Date()
				this.todayYear = this.nowYear = this.timestamp.getFullYear()
				this.todayMonth = this.nowMonth = this.timestamp.getMonth() + 1
				this.todayDay = this.nowDay = this.timestamp.getDate()
				this.clickActiveIndex = this.nowDay
				this.initDate()
			},
			
			getTriangleTopClass(item) {
				let style={
					to:0,
				}			
				let date=`${this.nowYear}-${parseInt(this.nowMonth)<10?'0'+this.nowMonth:this.nowMonth}-${parseInt(item)<10?'0'+item:item}`
		
				this.dateStatusList.forEach(val=>{						
					if(val.date===date && val.am){
						if(val.am.type==='1'){
							style.borderTop= '20rpx solid green'
						}else if(val.am.type==='2'){
							style.borderTop= '20rpx solid red'	
						}					
					}
				})
				return style
			},
			getTriangleBottomClass(item) {
				let style={
					bottom:0,							
				}
				let date=`${this.nowYear}-${parseInt(this.nowMonth)<10?'0'+this.nowMonth:this.nowMonth}-${parseInt(item)<10?'0'+item:item}`
				
				this.dateStatusList.forEach(val=>{		
					if(val.date===date && val.pm){
						if(val.pm.type==='1'){
							style.borderBottom= '20rpx solid green'
						}else if(val.pm.type==='2'){
							style.borderBottom= '20rpx solid red'	
						}					
					}			
				})	
				return style
			},
			initDate() {
				if ((this.nowYear % 4 === 0 && this.nowYear % 100 !== 0) || this.nowYear % 400 === 0) {
					this.monthList[1] = 29
				} else {
					this.monthList[1] = 28
				}
				const firstDay = this.getWeekDay(this.nowYear, this.nowMonth, 1) // 获得指定年月的1号是星期几
				this.weekDay = firstDay === 7 ? 0 : firstDay
				const remineDay = this.getWeekDay(this.nowYear, this.nowMonth, this.monthList[this.nowMonth - 1]) // 获得最后一天是星期几，往后填充多少个
				this.lastWeekDay = remineDay === 7 ? 6 : 6 - remineDay
				this.$emit('getNowDate',{
					year:this.nowYear,
					month:this.nowMonth,
					day:this.clickActiveIndex,
					isClick:false
				})
			},
			getWeekDay(year, month, day) { // 根据年月日获得为星期几
				return new Date(`${year}/${month}/${day}`).getDay()
			},
			selectTime(index) {
				this.clickActiveIndex = index
				this.$emit('getNowDate',{
					year:this.nowYear,
					month:this.nowMonth,
					day:index,
					isClick:true
				})
			},
			actionHandle(type) {
				switch (type) {
					case "prevYear":
						if (this.nowYear > 1991) {
							this.nowYear -= 1
						} else {
							this.nowYear = 1991
						}
						break
					case "nextYear":
						this.nowYear += 1
						break
					case "prevMonth":
						if (this.nowMonth > 1) {
							this.nowMonth -= 1
						} else {
							this.nowMonth = 12
							this.nowYear -= 1
						}
						break
					case "nextMonth":
						if (this.nowMonth < 12) {
							this.nowMonth += 1
						} else {
							this.nowMonth = 1
							this.nowYear += 1
						}
						break
					default:
						break
				}
				this.initDate()
			}
		}
	}
</script>

<style scoped lang='scss'>
	.a-calendar {
		.t-header {
			padding: 20rpx 0;

			.prev,
			.next {
				.margin-l-40 {
					margin-left: 40rpx;
				}
			}

			.title {
				margin: 0 40rpx;
				width: 200rpx;
				text-align: center;
			}
		}

		.t-info {
			margin: 20rpx 10rpx;
			height: 100rpx;
			background: #5FA6DF;
			border-radius: 4rpx;

			.info-box {
				&:not(:last-child) {
					border-right: 1rpx solid #FFFFFF;
				}

				.name {
					margin-bottom: 4rpx;
				}
			}
		}

		.t-body {
			margin: 0 10rpx;

			.cell {
				height: 80rpx;
				line-height: 80rpx;
				text-align: center;
			}

			.t-body-header {
				.week-cell {
					color: red;
				}

			}

			.t-body-main {

				.day-cell {
					position: relative;
					margin-bottom: 20rpx;
					.triangle-right{
						width: 0;
						height: 0;	
						right: 0;
						border-left: 20rpx solid transparent; 
					}
					&-active {
						background: #fef0f0;
						border-radius: 4rpx;
					}
					&-today{
						color: red !important;
					}
					&-disabled {
						color: #A8A8A8;
						cursor: not-allowed;

					}
				}
			}
		}

		.t-footer {
			margin: 20rpx;

			.t-footer-box {
				.color {
					width: 30rpx;
					height: 30rpx;
					margin-right: 6rpx;
				}
			}
		}
	}
</style>
