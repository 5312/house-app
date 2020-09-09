<template>
	<view class="shop-transfer-application">
		<a-navbar title="员工调店申请" @back="$tool.uniSwitchTab({url:'/pages/home/index'})"></a-navbar>
		<view class="content">
			<u-form :model="form" ref="uForm" label-width='140rpx' class="form form-top" label-position='top' :label-style="{color:'#a0cfff'}">
				<view class="title bold u-border-bottom">
					基本信息
				</view>
				<u-form-item label="姓名">
					<u-input v-model="form.name" />
				</u-form-item>
				<u-form-item label="职位">
					<u-input v-model="form.intro" />
				</u-form-item>
				<u-form-item label="所属店组">
					<u-input v-model="form.showNum" />
				</u-form-item>
				<view class="title bold u-border-bottom">
					操作信息
				</view>
				<u-form-item label="调店时间">
					<u-input v-model="form.time" type="select" @click="isShowCalendar = true" />
					<u-calendar v-model="isShowCalendar" mode="date" @change='calendarChange'></u-calendar>
				</u-form-item>
				<u-form-item label="调入组名">
					<u-input v-model="form.groupName" type="select" @click="isShowSelect = true" />
					<u-action-sheet :list="selectList" v-model="isShowSelect" @click="actionSheetCallback"></u-action-sheet>
				</u-form-item>
				<u-form-item label="调店原因">
					<u-radio-group v-model="form.leaveStatus" @change="val=>form.leaveStatus=val">
						<u-radio  v-for="(item, index) in leaveStatusList" :key="index" :name="item.name" shape="circle" >
							{{item.name}}
						</u-radio>
					</u-radio-group>
				</u-form-item>
				<u-form-item label="房源(房源不能跨区域带走)">
					<u-radio-group v-model="form.houseStatus" @change="val=>form.houseStatus=val">
						<u-radio  v-for="(item, index) in houseReasonList" :key="index" :name="item.name" shape="circle" >
							{{item.name}}
						</u-radio>
					</u-radio-group>
				</u-form-item>
				<u-form-item label="客源">
					<u-radio-group v-model="form.guestStatus" @change="val=>form.guestStatus=val">
						<u-radio  v-for="(item, index) in guestList" :key="index" :name="item.name" shape="circle" >
							{{item.name}}
						</u-radio>
					</u-radio-group>
				</u-form-item>
				<u-form-item label="端口是否赠送">
					<u-radio-group v-model="form.portStatus" @change="val=>form.portStatus=val">
						<u-radio  v-for="(item, index) in portList" :key="index" :name="item.name" shape="circle" >
							{{item.name}}
						</u-radio>
					</u-radio-group>
				</u-form-item>
				<u-form-item label="申请原因">
					<u-input v-model="form.reason" type='textarea'/>
				</u-form-item>
			</u-form>
			<u-button  class="custom-style" >提交</u-button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				form: {
					name: '',
					intro: '',
					showNum: '',
					time: '',
					groupName: '',
					leaveStatus:'',
					houseStatus:'',
					guestStatus:'',
					portStatus:'',
					reason:""
				},
				portList: [{
					name: '是'
				}, {
					name: '否'
				},{
					name: '暂无端口'
				}],
				guestList: [{
					name: '带走'
				}, {
					name: '不带走'
				}],
				houseReasonList: [{
					name: '带走'
				}, {
					name: '不带走'
				}],
				leaveStatusList: [{
					name: '本人申请'
				}, {
					name: '公司调配'
				}],
				isShowCalendar: false,
				isShowSelect: false,
				selectList: [{
						text: '红旗店01组'
					},
					{
						text: '红旗店02组'
					},
					{
						text: '红旗店03组'
					}
				]
			}
		},
		methods: {
			calendarChange(val) {
				console.log(val)
				this.form.time = val.result
			},
			actionSheetCallback(index) {
				this.form.groupName = this.selectList[index].text
			},
			radioGroupChange(val){
				console.log(val)
				this.form[val]=val
				
			}
		}
	}
</script>

<style scoped lang="scss">
	.shop-transfer-application {
		.custom-style {
			color: #FFFFFF;
			background: #78c340;
		}	
		.content {
			padding: 0 40rpx 40rpx 40rpx;
			
			.form {
				.title {
					height: 110rpx;
					line-height: 110rpx;
					font-size: 30rpx;
				}
				/deep/.u-form-item{
					line-height: 40rpx;
				}
			}
		}
	}
</style>
