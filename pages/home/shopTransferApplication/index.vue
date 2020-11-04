<template>
	<view class="shop-transfer-application">
		<a-navbar title="员工调店申请" @back="$tool.uniSwitchTab({url:'/pages/home/index'})"></a-navbar>
		<view class="content" v-show="show">
			<u-form :model="form" ref="uForm" label-width='140rpx' class="form form-top" label-position='top' v-if='form' :error-type="errorType">
				<view class="title bold u-border-bottom">
					<u-icon size="28" name="calendar" class="mar"></u-icon><text>基本信息</text>
				</view>
				<u-form-item label="姓名">
					<u-input v-model="form.ygmingcheng" disabled />
				</u-form-item>
				<u-form-item label="工号">
					<u-input v-model="form.ygbianhao" disabled />
				</u-form-item>
				<u-form-item label="职位">
					<u-input v-model="form.gangwei" disabled />
				</u-form-item>
				<u-form-item label="所属店组">
					<u-input v-model="form.bumen" disabled />
				</u-form-item>
				<view class="margin-box"></view>
				<view class="title bold u-border-bottom ">
					<u-icon size="28" name="calendar" class="mar"></u-icon><text>操作信息</text>
				</view>
				<u-form-item label="调店时间" :required="true" prop='sxshijian'>
					<u-input v-model="form.sxshijian" placeholder="请选择时间" type="select" @click="isShowCalendar = true" />
					<u-calendar v-model="isShowCalendar" mode="date" @change='calendarChange' :isAceClick='true'></u-calendar>

				</u-form-item>
				<u-form-item label="调入组名" :required="true" prop='groupName'>
					<u-input v-model="form.groupName" placeholder="请选择店组名" type="select" @click="actionSheet('groupName')" />
					<u-select v-model="groupName" :list="form.bumenlist" @confirm="confirm1"></u-select>

				</u-form-item>
				<u-form-item label="调动职务" :required="true" prop='zhiwuName'>
					<u-input v-model="form.zhiwuName" placeholder="请选择调动职务" type="select" @click="actionSheet('zhiwu')" />
					<u-select v-model="zhiwu" :list="form.zhiwu" @confirm="confirm2"></u-select>

				</u-form-item>
				<u-form-item label="调动类型" :required="true" prop='biandongName'>
					<u-input v-model="form.biandongName" placeholder="请选择店组名" type="select" @click="actionSheet('biandong')" />
					<u-select v-model="biandong" :list="form.biandong" @confirm="confirm3"></u-select>
				</u-form-item>
				<u-form-item label="调店原因" :required="true" prop="diao">
					<u-radio-group v-model="form.diao" @change="diaoChange">
						<u-radio v-for="(item, index) in leaveStatusList" :key="index" :name="item.value" shape="circle">
							{{item.name}}
						</u-radio>
					</u-radio-group>
				</u-form-item>
				<u-form-item label="房源接收人" :required="true" prop='fzouName'>
					<u-input v-model="form.fzouName" placeholder="请选择房源接收人" type="select" @click="actionSheet('fzou')" />
					<u-select v-model="fzou" :list="form.yhlist" @confirm="confirm4"></u-select>

				</u-form-item>
				<u-form-item label="客源接收人" :required="true" prop="yzouName">
					<u-input v-model="form.yzouName" placeholder="请选择客源接收人" type="select" @click="actionSheet('yzou')" />
					<u-select v-model="yzou" :list="form.yhlist" @confirm="confirm5"></u-select>

				</u-form-item>
				<u-form-item label="申请原因" :required="true" prop="bdyuanyin">
					<u-input v-model="form.bdyuanyin" type='textarea' />
				</u-form-item>
			</u-form>
			<u-button class="custom-style" @click='validataSublime'>提交</u-button>
		</view>
	</view>
</template>

<script>
	import api from "@/utils/api/resign.js"; //api
	export default {
		data() {
			return {
				show:false,
				housed: false,
				kd: false,
				errorType:['message'],
				form: {
					sxshijian: ''
				},
				rules: {
					sxshijian: [ // 必填规则
						{
							required: true,
							message: '请选择调店时间',
							// blur和change事件触发检验
							//trigger: ['blur', 'change'],
						},
					],
					groupName:[ // 必填规则
						{
							required: true,
							message: '请选择调入组名',
							// blur和change事件触发检验
							//trigger: ['blur', 'change'],
						},
					],
					zhiwuName:[ // 必填规则
						{
							required: true,
							message: '请选择调动职务',
							// blur和change事件触发检验
							//trigger: ['blur', 'change'],
						},
					],
					biandongName:[ // 必填规则
						{
							required: true,
							message: '请选择调动类型',
							// blur和change事件触发检验
							//trigger: ['blur', 'change'],
						},
					],
					diao:[ // 必填规则
						{
							required: true,
							message: '请选择	调店原因',
							// blur和change事件触发检验
							//trigger: ['blur', 'change'],
						},
					],
					fzouName:[ // 必填规则
						{
							required: true,
							message: '请选择房源接收人',
							// blur和change事件触发检验
							//trigger: ['blur', 'change'],
						},
					],
					yzouName:[ // 必填规则
						{
							required: true,
							message: '请选择客源接收人',
							// blur和change事件触发检验
							//trigger: ['blur', 'change'],
						},
					],
					bdyuanyin:[ // 必填规则
						{
							required: true,
							message: '请填写申请原因',
							// blur和change事件触发检验
							trigger: ['blur', 'change'],
						},
					],
				},
				groupName: false,
				zhiwu: false,
				biandong: false,
				fzou: false,
				yzou: false,
				leaveStatusList: [{
					name: '本人申请',
					value: 1
				}, {
					name: '公司调配',
					value: 2
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
		// 必须要在onReady生命周期，因为onLoad生命周期组件可能尚未创建完毕
		onReady() {
			this.$refs.uForm.setRules(this.rules);

		},
		onLoad() {
			this.getDetail()
		},
		methods: {
			diaoChange(e) {
				this.form.diao = e
			},
			getDetail() {
				let _this = this;
				this.$tool.uniRequest({
					url: "rsdangan/biandong",
					method: 'GET',
					success: (res) => {
						if (res.id) {
							_this.$tool.uniRedirectTo({
								url: `/pages/home/shopTransferApplication/newpage?id=${res.id}`
							})
							return;
						}else{
							_this.show = true;
						}

						this.form = res
						this.form.bumenlist.forEach(item => {
							item.label = item.name
							item.value = item.id
						})
						this.form.zhiwu.forEach(item => {
							item.label = item.remark
							item.value = item.id
						})
						this.form.biandong.forEach(item => {
							item.label = item.lxming
							item.value = item.xsshunxu
						})
						this.form.yhlist.forEach(item => {
							item.label = item.ygmingcheng
							item.value = item.uid
						})
					}
				})
			},
			confirm1(e) {
				this.form.groupName = e[0].label
				this.form.groupNameId = e[0].value;
				
			},
			confirm2(e) {
				this.form.zhiwuName = e[0].label
				this.form.zhiwuNameId = e[0].value
			},
			confirm3(e) {
				this.form.biandongName = e[0].label
				this.form.biandongNameId = e[0].value
			},
			confirm4(e) {
				this.form.fzouName = e[0].label
				this.form.fzouNameId = e[0].value
			},
			confirm5(e) {
				this.form.yzouName = e[0].label
				this.form.yzouNameId = e[0].value
			},
			validataSublime() {
				//console.log(this.form)
				this.$refs.uForm.validate(valid => {
					//console.log(valid)
					if (valid) {
						console.log('验证成功')
						this.submit();
					} else {
						console.log('验证失败');
					}
				});
			},
			submit() {
				let params = {
					sxshijian: this.form.sxshijian || '',
					bumen: this.form.groupNameId || '',
					diao: this.form.diao || '',
					fzou: this.form.fzouNameId || '',
					yzou: this.form.yzouNameId || '',
					biandong: this.form.biandongNameId || '',
					bdyuanyin: this.form.bdyuanyin || '',
					zhiwu: this.form.zhiwuNameId || '',
				}
				api.bianDong(params).then(res => {
					this.$tool.uniShowToast({
						title: "调店申请成功！"
					})
					setTimeout(() => {
						this.$tool.uniRedirectTo({
							url: `/pages/home/shopTransferApplication/newpage?id=${res.id}`
						})
					}, 1000)
				})

			},
			actionSheet(type) {
				this[type] = true
			},
			calendarChange(val) {
				this.form.sxshijian = val.result
			}
		}
	}
</script>

<style scoped lang="scss">
	.mar {
		margin-right: 10rpx;
	}

	.margin-box {
		position: relative;
		left: -100rpx;
		width: 200%;
		height: 20rpx;
		background-color: #EDEDED;
	}

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

				/deep/.u-form-item {
					line-height: 40rpx;
				}
			}
		}
	}
</style>
