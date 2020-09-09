<template>
	<view class="clock-in">		
		<a-navbar title="考勤打卡" @back="$tool.uniSwitchTab({url:'/pages/home/index'})"></a-navbar>
		<u-tabs :list="list" :is-scroll="false" :current="current" @change="change" height='100'></u-tabs>
		<view class="content">
			<view class="content-1" v-show="current===0">
				<view class="map-box">
					<map style="width: 100%; height: 300px;" :latitude="latitude" :longitude="longitude" :markers="covers" :scale="18">
					</map>
				</view>
				<view class="info-box">
					<view class="line">上班时间：{{workTime()}}</view>
					<view class="line">下班时间：无记录 未签到</view>
					<view class="line">当前职务：实习置业顾问</view>
				</view>
				<view class="count-box flex a-center j-center">
					<view class="count-bg radius flex a-center j-center">
						<u-count-down :timestamp="timestamp" bg-color="transparent" color="#fff" separator-color="#fff"></u-count-down>
					</view>
				</view>
			</view>
			<view class="content-2" v-show="current===1">
				<view>
						1
				</view>
			</view>
			<view class="content-3" v-if="current===2">
				<view class="record-box">
					<a-calendar  ref='calendarDom' ></a-calendar>
				</view>
				<view class="record-time">
					<view class="line border-bottom border-top">上班：</view>
					<view class="line border-bottom">下班：</view>
					<view class="line-info">提示：未审核考勤将不计算工资，请联系上一级及时审核！</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				isShowCalendar:true,
				list: [{
					name: '正常打卡'
				}, {
					name: '外勤打卡'
				}, {
					name: '打卡记录'
				}],
				current: 0,
				latitude: 39.909,
				longitude: 116.39742,
				covers: [{
					latitude: 39.909,
					longitude: 116.39742,
					iconPath: '../../../static/location.png'
				}],
				timestamp:86400
			}
		},
		
		methods: {
			change(index) {
				this.current = index;
			},
			workTime(){
				let timer=new Date().getTime()
				let result=this.$u.timeFormat(timer, 'yyyy-mm-dd hh:MM')
				return result
			},
			
		}
	}
</script>

<style scoped lang="scss">
	.clock-in{
		.content{
			.content-1{
				.info-box{
					padding: 30rpx 40rpx;
					.line{
						height: 60rpx;
						line-height: 60rpx;
					}
				}
				.count-box{
					margin-top: 40rpx;
					.count-bg{
						width: 200rpx;
						height: 200rpx;
						background: #c8c9cc;
						color:#FFFFFF;
						
					}
				}
			}
			.content-3{
				.record-time{				
					.line{
						height: 80rpx;
						line-height: 80rpx;
						padding: 0 40rpx;
					}
					.line-info{
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
