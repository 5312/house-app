<template>
	<view class="quit-application">
		<a-navbar title="离职申请" @back="$tool.uniSwitchTab({url:'/pages/home/index'})"></a-navbar>
		<view class="content">
			<u-form :model="form" ref="uForm" label-width='140rpx' class="form form-top" label-position='top' :label-style="{color:'#a0cfff'}">
				<view class="title bold u-border-bottom">
					基本信息
				</view>
				<u-form-item label="姓名">
					<view>{{form.name}}</view>
				</u-form-item>
				<u-form-item label="职位">
					<view>{{form.intro}}</view>
				</u-form-item>
				<u-form-item label="所属店组">			
					<view>{{form.showNum}}</view>
				</u-form-item>
				<u-form-item label="入职时间">
					<view>{{form.showNum}}</view>
				</u-form-item>
				<u-form-item label="学历">
					<view>{{form.showNum}}</view>
				</u-form-item>
				<u-form-item label="专业">
					<view>{{form.showNum}}</view>
				</u-form-item>
				<view class="title bold u-border-bottom">
					操作信息
				</view>
				<u-form-item label="手机号码">
					<view>{{form.showNum}}</view>
				</u-form-item>
				<u-form-item label="短号">
					<view>{{form.showNum}}</view>
				</u-form-item>		
				<u-form-item label="离职类型">
					<u-input v-model="form.groupName" type="select" @click="isShowSelect = true" />
					<u-action-sheet :list="selectList" v-model="isShowSelect" @click="actionSheetCallback"></u-action-sheet>
				</u-form-item>
				<u-form-item label="离职时间">
					<u-input v-model="form.time" type="select" @click="isShowCalendar = true" />
					<u-calendar v-model="isShowCalendar" mode="date" @change='calendarChange'></u-calendar>
				</u-form-item>
				<u-form-item label="档案是否在公司">
					<u-radio-group v-model="form.leaveStatus" @change="val=>form.leaveStatus=val">
						<u-radio  v-for="(item, index) in leaveStatusList" :key="index" :name="item.name" shape="circle" >
							{{item.name}}
						</u-radio>
					</u-radio-group>
				</u-form-item>
				<u-form-item label="未领工资起止日期">
					<u-input v-model="form.time" type="select" @click="isShowRangeCalendar = true" />
					<u-calendar v-model="isShowRangeCalendar" mode="range" @change='calendarChange'></u-calendar>
				</u-form-item>
				<u-form-item label="房源接收人">
					<u-input v-model="form.groupName" type="select" @click="isShowSelect = true" />
					<u-action-sheet :list="selectList" v-model="isShowSelect" @click="actionSheetCallback"></u-action-sheet>
				</u-form-item>
				<u-form-item label="客源接收人">
					<u-input v-model="form.groupName" type="select" @click="isShowSelect = true" />
					<u-action-sheet :list="selectList" v-model="isShowSelect" @click="actionSheetCallback"></u-action-sheet>
				</u-form-item>
				<u-form-item label="端口接受人">
					<u-input v-model="form.groupName" type="select" @click="isShowSelect = true" />
					<u-action-sheet :list="selectList" v-model="isShowSelect" @click="actionSheetCallback"></u-action-sheet>
				</u-form-item>
				<u-form-item label="银行卡类型">
					<u-input v-model="form.groupName" type="select" @click="isShowSelect = true" />
					<u-action-sheet :list="selectList" v-model="isShowSelect" @click="actionSheetCallback"></u-action-sheet>
				</u-form-item>
				<u-form-item label="银行卡号">
					<u-input v-model="form.groupName"  />
					
				</u-form-item>
				<u-form-item label="离职原因">
					<u-input v-model="form.reason" type='textarea'/>
				</u-form-item>
				<u-form-item label="对公司的建议">
					<u-input v-model="form.reason" type='textarea'/>
				</u-form-item>
			</u-form>
			<u-button  class="custom-style">提交</u-button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				isShowRangeCalendar:false,
				form: {
					name: 'ace',
					intro: 'ace',
					showNum: 'ace',
					time: 'ace',
					groupName: 'ace',
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
					name: '是'
				}, {
					name: '否'
				}],
				isShowCalendar: false,
				isShowSelect: false,
				selectList: [{
						text: '主动离职'
					},
					{
						text: '被动离职'
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
	.quit-application {
		.custom-style {
			color: #FFFFFF;
			background: #78c340;
		}	
		.content {
			padding: 0 40rpx;
			margin-bottom: 40rpx;
			.form {
				.title {
					height: 110rpx;
					line-height: 110rpx;
					font-size: 30rpx;
				}
			}
		}
	}
</style>
