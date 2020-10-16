<template>
	<view class="shop-transfer-application">
		<a-navbar title="离职申请" @back="$tool.uniSwitchTab({url:'/pages/home/index'})"></a-navbar>
		<view class="content">
			<u-form :model="form" ref="uForm" label-width='140rpx' class="form form-top" label-position='top' v-if="form"
			 :error-type="errorType">
				<view class="title bold u-border-bottom">
					<u-icon size="28" name="man-add" class="mar"></u-icon><text>基本信息</text>
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
				<u-form-item label="入职时间">
					<u-input v-model="form.rzshijian" disabled />
				</u-form-item>
				<view class="margin-box"></view>
				<!--  -->
				<view class="title bold u-border-bottom ">
					<u-icon size="28" name="calendar" class="mar"></u-icon><text>操作信息</text>
				</view>
				<u-form-item label="离职类型" :required="true" prop='lizhiyyName'>
					<u-input v-model="form.lizhiyyName" placeholder="请选择离职类型" type="select" @click="actionSheet('lizhiyy')" />
					<u-select v-model="lizhiyy" :list="form.lizhiyy" @confirm="confirm1"></u-select>

				</u-form-item>
				<u-form-item label="离职时间" :required="true" prop='lizhitime'>
					<u-input v-model="form.lizhitime" placeholder="请选择离职时间" type="select" @click="isShowCalendar = true" />
					<u-calendar v-model="isShowCalendar" max-date='40000' mode="date" @change='calendarChange' :isAceClick='true'></u-calendar>

				</u-form-item>

				
				<u-form-item label="档案是否在公司" :required="true" prop='dashifou'>
					<u-radio-group v-model="form.dashifou" @change="dashifouChange">
						<u-radio v-for="(item, index) in leaveStatusList" :key="index" :name="item.value" shape="circle">
							{{item.name}}
						</u-radio>
					</u-radio-group>
				</u-form-item>
				<u-form-item label="房源接收人" :required="true" prop='fzouName'>
					<u-input v-model="form.fzouName" placeholder="请选择房源接收人" type="select" @click="actionSheet('fzou')" />
					<u-select v-model="fzou" :list="form.yhlist" @confirm="confirm4"></u-select>

				</u-form-item>
				<u-form-item label="客源接收人" :required="true" prop='yzouName'>
					<u-input v-model="form.yzouName" placeholder="请选择客源接收人" type="select" @click="actionSheet('yzou')" />
					<u-select v-model="yzou" :list="form.yhlist" @confirm="confirm5"></u-select>

				</u-form-item>
				<u-form-item label="银行卡类型" :required="true" prop='bankname'>
					<u-input v-model="form.bankname" placeholder="请选择银行卡类型" type="select" @click="actionSheet('bankname')" />
					<u-select v-model="bankname" :list="form.yinhang" @confirm="confirm3"></u-select>

				</u-form-item>
				<u-form-item label="银行卡号" :required="true" prop='banknum'>
					<u-input v-model="form.banknum"  placeholder="请输入银行卡号" type="number" />
					<!-- <u-keyboard :mask="false" ref="uKeyboard" @change="valChange" @backspace="backspace" v-model="keyNum"></u-keyboard> -->
				</u-form-item>
				<u-form-item label="离职原因" :required="true" prop='bdyuanyin'>
					<u-input v-model="form.bdyuanyin" type='textarea' />
				</u-form-item>
			</u-form>
			<u-button class="custom-style" @click="refsSubmit">提交</u-button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				errorType: ['message'],
				keyNum: false,
				lizhiyy: false,
				isShowCalendar: false,
				isShowRangeCalendar: false,
				fzou: false,
				yzou: false,
				bankname: false,
				params: {
					year: true,
					month: true,
					day: true,
				},
				moneys: '',
				moneys1: '',
				money: false,
				money1: false,
				housed: false,
				kd: false,
				portAccept: '',
				form: {
					banknum: ''

				},
				rules: {
					lizhiyyName:[ // 必填规则
						{
							required: true,
							message: '请选择离职类型',
							// blur和change事件触发检验
							trigger: ['blur', 'change'],
						},
					],
					lizhitime:[ // 必填规则
						{
							required: true,
							message: '请选择离职时间',
							// blur和change事件触发检验
							trigger: ['blur', 'change'],
						},
					],
					dashifou:[ // 必填规则
						{
							required: true,
							message: '请选择',
							// blur和change事件触发检验
							//trigger: ['blur', 'change'],
						},
					],
					fzouName:[ // 必填规则
						{
							required: true,
							message: '请选择房源接受人',
							// blur和change事件触发检验
							trigger: ['blur', 'change'],
						},
					],
					yzouName:[ // 必填规则
						{
							required: true,
							message: '请选择客源接受人',
							// blur和change事件触发检验
							trigger: ['blur', 'change'],
						},
					],
					bankname:[ // 必填规则
						{
							required: true,
							message: '请选择银行卡类型',
							// blur和change事件触发检验
							trigger: ['blur', 'change'],
						},
					],
					banknum:[ // 必填规则
						{
							required: true,
							message: '请输入银行卡号',
							// blur和change事件触发检验
							trigger: ['blur', 'change'],
						},
					],
					bdyuanyin:[ // 必填规则
						{
							required: true,
							message: '请输入离职原因',
							// blur和change事件触发检验
							trigger: ['blur', 'change'],
						},
					],
				},
				portList: [{
					name: '是'
				}, {
					name: '否'
				}, {
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
					name: '是',
					value: 1
				}, {
					name: '否',
					value: 2
				}],
				isShowSelect: false,
				isShowSelect1: false,
				isShowSelect2: false,
				isShowSelect3: false,
				isShowSelect4: false,
				isShowSelect5: false,
				isShowSelect6: false,
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
			// 按键被点击(点击退格键不会触发此事件)
			valChange(val) {
				// 将每次按键的值拼接到value变量中，注意+=写法
				console.log(this.form)
				this.form.banknum += val;
				console.log(this.form.banknum);
			},
			// 退格键被点击
			backspace() {
				// 删除value的最后一个字符
				if (this.form.banknum.length) this.form.banknum = this.form.banknum.substr(0, this.form.banknum.length - 1);
				console.log(this.form.banknum);
			},
			getDetail() {
				let _this = this
				this.$tool.uniRequest({
					url: "rsdangan/lizhi",
					method: 'GET',
					success: (res) => {
						console.log(res)
						if (res.id) {
							_this.$tool.uniRedirectTo({
								url: `/pages/home/quitApplication/goout?id=${res.id}`
							})
							return;
						}
						res.banknum = '';
						this.form = res
						this.form.lizhiyy.forEach(item => {
							item.label = item.bankname
							item.value = item.vals
						})
						this.form.yinhang.forEach(item => {
							item.label = item.bankname
							item.value = item.vals
						})

						this.form.yhlist.forEach(item => {
							item.label = item.ygmingcheng
							item.value = item.uid
						})
					}
				})
			},
			dashifouChange(val) {
				this.form.dashifou = val
			},
			calendarChange(val) {
				this.form.lizhitime = val.result
			},
			confirm1(e) {
				this.form.lizhiyyName = e[0].label
				this.form.lizhiyyNameId = e[0].value
			},
			confirm3(e) {
				this.form.bankname = e[0].label
				this.form.banknameId = e[0].value
			},
			confirm4(e) {
				this.form.fzouName = e[0].label
				this.form.fzouNameId = e[0].value
			},
			confirm5(e) {
				this.form.yzouName = e[0].label
				this.form.yzouNameId = e[0].value
			},
			actionSheet(type) {
				this[type] = true
			},
			calendarRangeChange(val) {
				this.form.aceTime = val.startDate + "~" + val.endDate
				this.form.gzstime = val.startDate
				this.form.gzetime = val.endDate
			},
			refsSubmit() {
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
					lizhiyy: this.form.lizhiyyNameId || '',
					lizhitime: this.form.lizhitime || '',
					dashifou: this.form.dashifou || '',
					gzstime: this.form.gzstime || '',
					gzetime: this.form.gzetime || '',
					yzou: this.form.yzouNameId || '',
					fzou: this.form.fzouNameId || '',
					bankname: this.form.banknameId || '',
					banknum: this.form.banknum || '',
					bdyuanyin: this.form.bdyuanyin || '',
				}
				this.$tool.uniRequest({
					url: "rsdangan/lizhi",
					method: 'POST',
					params,
					success: (res) => {
						this.$tool.uniShowToast({
							title: "离职申请提交成功！"
						})
						setTimeout(() => {
							this.$tool.uniRedirectTo({
								url: `/pages/home/quitApplication/goout?id=${res.id}`
							})
						}, 1000)
					}
				})
			},


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
