<template>
	<view>

		<view class="wrap" v-show="!commAdd">
			<a-navbar title="新增报单" @back="$tool.uniRedirectTo({url:'/pages/home/reimbursement/index'})"></a-navbar>
			<view class="main">
				<u-form class="form" :model="form" ref="uForm" :error-type="errorType">
					<u-form-item class="bg" label-width='150' label-align='rigth' label="报单类型:">
						<view>{{leixing}}</view>
					</u-form-item>
					<u-form-item class="bg" :required='true' prop="fwleixingLabel" label-width='150' label-align='rigth' label="房屋类型:"
					 v-if='formSelectInfo'>
						<u-input :selectOpen="fangType" placeholder="" type="select" v-model="form.fwleixingLabel" @click='showSelect("fangType")' />
						<u-select v-model="fangType" :list="formSelectInfo.fwleixing" @confirm="confirm1"></u-select>
					</u-form-item>
					<u-form-item class="bg" :required='true' prop="fdizhi" label-width='150' label-align='rigth' label="房屋地址:">
						<u-input v-model="form.fdizhi" placeholder="请输入地址" :disabled="isDisable" />
					</u-form-item>

					<u-form-item class="bg " :required='true' prop="fanghao" label-width='150' label-align='rigth' label="门牌号">
						<view class="inline">
							<view class="flex  input">
								<u-input class="item" :border='true' :disabled="isDisable" v-model.number="form.zuodong" placeholder='栋' :type="type" />
								<u-input class="item" :border='true' :disabled="isDisable" v-model.number="form.danyuan" placeholder='单元' :type="type" />
								<u-input class="item" :border='true' :disabled="isDisable" v-model.number="form.fanghao" placeholder='房号' :type="type" />
							</view>
						</view>
					</u-form-item>

					<u-form-item class="bg" :required='true' prop="yezhu" label-width='150' label-align='rigth' label="业主姓名:">
						<u-input v-model="form.yezhu" placeholder="请业主姓名" :disabled="isDisable" />
					</u-form-item>
					<u-form-item class="bg" :required='true' prop="yezhudianhua" label-width='150' label-align='rigth' label="业主电话:">
						<u-input v-model="form.yezhudianhua" placeholder="请业主电话" :disabled="isDisable" />
					</u-form-item>

					<u-form-item class="bg" :required='true' prop="khxingming" label-width='150' label-align='rigth' label="客户姓名:">
						<u-input v-model="form.khxingming" placeholder="请输入客户姓名" :disabled="isDisable" />
					</u-form-item>
					<u-form-item class="bg" :required='true' prop="dianhua" label-width='150' label-align='rigth' label="客户电话:">
						<u-input v-model="form.dianhua" placeholder="请输入客户电话" :disabled="isDisable" />
					</u-form-item>
					<u-form-item class="bg" :required='true' prop="cjjiage" label-width='150' label-align='rigth' label="成交价格:">
						<u-input v-model="form.cjjiage" placeholder='xxx元' :disabled="isDisable" />
					</u-form-item>
					<u-form-item class="bg" :required='true' prop="cjtime" label-width='150' label-align='rigth' label="成交时间:">
						<u-input :selectOpen="show" placeholder="" v-model="form.cjtime" type="select" @click='showSelect("show")' />
						<u-calendar max-date='5000' v-model="show" mode="date" @change="calendarChange"></u-calendar>
					</u-form-item>
					<u-gap height="40" bg-color="#F8F8F8"></u-gap>
					<!--end -->
					<!-- 中介类费用 -->
					<u-cell-group>
						<u-cell-item :required='true' prop='zjinfos' title="中介类费用" value="" :arrow="false" @click='commAdd = true;comtype=1'>
							<u-icon slot='right-icon' name="plus"></u-icon>
						</u-cell-item>
					</u-cell-group>
					<u-swipe-action :show="item.show" :index="index" v-for="(item, index) in zjinfos" :key="index+'&*'+item" @click="click2"
					 @open="open2" :options="options">
						<u-row class="listBlue" gutter="10" justify="around">
							<u-col span="7" text-align='center'>
								<view>{{item.project}}</view>
							</u-col>
							<u-col span="4">
								<view>{{item.price}}元</view>
							</u-col>
						</u-row>
					</u-swipe-action>
					<!--end -->
					<!-- 分配业绩 -->
					<u-cell-group>
						<u-cell-item :required='true' prop='outstandingList' title="分配业绩" :arrow="false" value="" @click='preList'>
							<u-icon slot='right-icon' name="plus"></u-icon>
						</u-cell-item>
					</u-cell-group>
					<u-swipe-action :show="item.show" :index="index" v-for="(item, index) in outstandingList" :key="item.peopleId+'&'+index"
					 @click="click" @open="open" :options="options">
						<u-row class="listRed" gutter="10" justify="between">
							<u-col span="3" text-align='center'>
								<view>{{item.people}}</view>
							</u-col>
							<u-col span="3">
								<view>{{item.reason}}</view>
							</u-col>
							<u-col span="2">
								<view>{{item.scale}}</view>
							</u-col>
							<u-col span="4">
								<view>{{item.outs}}元</view>
							</u-col>
						</u-row>
					</u-swipe-action>
					<!-- 其他费用 -->
					<u-cell-group>
						<u-cell-item :required='true' prop='otherType' title="其他费用" value="" :arrow="false" @click='commAdd = true;comtype=0'>
							<u-icon slot='right-icon' name="plus"></u-icon>
						</u-cell-item>
					</u-cell-group>
					<u-swipe-action :show="item.show" :index="index" v-for="(item, index) in otherType" :key="item.projectId+'-'+item.price+index"
					 @click="click1" @open="open1" :options="options">
						<u-row class="listBlue" gutter="10" justify="around">
							<u-col span="7" text-align='center'>
								<view>{{item.project}}</view>
							</u-col>
							<u-col span="4">
								<view>{{item.price}}元</view>
							</u-col>
						</u-row>
					</u-swipe-action>
					<!-- end -->
					<u-form-item :required='true' prop="other" class="bg" label-width='150' label-align='rigth' label="其他费用:">
						<u-input v-model="form.other + '元'" placeholder='xxx元' :disabled="true" />
					</u-form-item>

					<u-form-item :required='true' prop="ysyongjin" class="bg" label-width='150' label-align='rigth' label="成交业绩:">
						<u-input v-model="form.ysyongjin + '元'" placeholder='xxx元' :disabled="true" />
					</u-form-item>
					<!-- end -->

					<u-form-item class="bg" label-width='150' label-align='rigth' label="备注:">
						<u-input type='textarea' v-model="form.beizhu" placeholder="" :disabled="isDisable" />
					</u-form-item>

					<view class="steps bg margin-bottom margin-top">
						<view class="small-title">审批流程</view>
						<u-steps direction='column' mode="dot" :list="numList" :current="0"></u-steps>
					</view>
					<view class="btn">
						<button class="bt" @click="refsSubmit">提交</button>
					</view>
				</u-form>
			</view>
		</view>
		<comadd v-if="commAdd" :ysyongjin='form.ysyongjin*1' v-on:back='back' v-on:update='update' :outstandingList='outstandingList'
		 :types='comtype'></comadd>
	</view>
</template>

<script>
	import comadd from './components/components'
	import unit from '../../../utils/unit.js';
	export default {
		components: {
			comadd
		},
		data() {
			return {
				comtype: 0,
				commAdd: false,
				isDisable: false,
				fangType: false,
				xiaoqu: false,
				show: false,
				formSelectInfo: null,
				currentIndex: 0,
				leixing: null,
				outstandingList: [], //业绩列表
				leixingList: [{
						name: '售单'
					},
					{
						name: '租单'
					}, {
						name: '新盘'
					}
				],
				errorType: ['message'],

				form: {
					leixing: null,
					fwleixingLabel: '',
					fwleixingValue: '',
					fdizhi: '',
					xiaoquLabel: '',
					xiaoquValue: '',
					symianji: '',
					zuodong: '',
					fanghao: '',
					danyuan: '',
					shi: '',
					ting: '',
					wei: '',
					yangtai: '',
					yezhu: '',
					yezhudianhua: '',
					yezhulx: 1,
					khxingming: '',
					dianhua: '',
					kehulx: '',
					cjtime: '',
					cjjiage: '',
					ysyongjin: 0,
					beizhu: '',
					other: 0
				},
				rules: {
					yezhu: [ // 必填规则
						{
							required: true,
							message: '请填写业主姓名',
							// blur和change事件触发检验
							trigger: ['blur', 'change'],
						},
					],
					fwleixingLabel: [ // 必填规则
						{
							required: true,
							message: '请填写房屋类型',
							// blur和change事件触发检验
							trigger: ['blur', 'change'],
						},
					],
					fdizhi: [ // 必填规则
						{
							required: true,
							message: '请填写房屋地址',
							// blur和change事件触发检验
							trigger: ['blur', 'change'],
						},
					],
					xiaoquValue: [ // 必填规则
						{
							required: true,
							message: '请填写所属小区',
							// blur和change事件触发检验
							trigger: ['blur', 'change'],
						},
					],
					fanghao: [ // 必填规则
						{
							required: true,
							message: '请填写房号',
							// blur和change事件触发检验
							trigger: 'blur',
							// 自定义验证函数，见上说明
							validator: (rule, value, callback) => {
								// 上面有说，返回true表示校验通过，返回false表示不通过
								// this.$u.test.mobile()就是返回true或者false的
								if(value){
									return true
								}
								return false;
							},
						},
					],
					yezhudianhua: [ // 必填规则
						{
							required: true,
							message: '请填写业主电话',
							// blur和change事件触发检验
							trigger: ['blur', 'change'],
						},
					],
					khxingming: [ // 必填规则
						{
							required: true,
							message: '请填写客户姓名',
							// blur和change事件触发检验
							trigger: ['blur', 'change'],
						},
					],
					dianhua: [ // 必填规则
						{
							required: true,
							message: '请填写客户电话',
							// blur和change事件触发检验
							trigger: ['blur', 'change'],
						},
					],
					cjtime: [ // 必填规则
						{
							required: true,
							message: '请填写成交时间',
							// blur和change事件触发检验
							trigger: ['blur', 'change'],
						},
					],
					cjjiage: [ // 必填规则
						{
							required: true,
							message: '请填写成交价格',
							// blur和change事件触发检验
							trigger: ['blur', 'change'],
						},
					],
					ysyongjin: [ // 必填规则
						{
							required: true,
							message: '请填写成交业绩',
							// blur和change事件触发检验
							trigger: ['blur', 'change'],
						},
					],
					other: [ // 必填规则
						{
							required: true,
							message: '请填写其他费用',
							// blur和change事件触发检验
							trigger: ['blur', 'change'],
						},
					],
				},
				otherType: [],
				zjinfos: [],
				current: 0,
				listSex: [{
						name: '先生',
						value: 1,
						disabled: false
					},
					{
						name: '女士',
						value: 2,
						disabled: false
					},
				],
				options: [{
					text: '删除',
					style: {
						backgroundColor: '#dd524d'
					}
				}],
				type: 'text',
				numList: [{
						name: '店长审核'
					},
					{
						name: '区长审核'
					},
					{
						name: '后台审核'
					},
					{
						name: '结案'
					}
				],

			}
		},
		// 必须要在onReady生命周期，因为onLoad生命周期组件可能尚未创建完毕
		onReady() {
			this.$refs.uForm.setRules(this.rules);

		},
		onLoad(options) {
			this.leixing = options.type
			this.init()
		},
		methods: {
			preList() { //分配业绩
				if (this.form.ysyongjin) {
					this.commAdd = true;
					this.comtype = 2;
				} else {
					this.$u.toast(`请选择中介类费用`);
				}
			},
			back() {
				this.commAdd = false;
			},
			update(val) {
				this.commAdd = false;
				const sum = function(arr, vals) { //数组金额之和
					if (arr.length <= 0) return 0;
					let other = arr.reduce(function(prev, curr, idx, arr) {
						let num = parseInt(prev.price || prev) + parseInt(curr.price);

						return num;
					});
					if (typeof(other) == 'object') {
						other = other.price;
					}
					return other;
				}
				if (this.comtype == 0) { //
					this.otherType.push(val);
					this.form.ysyongjin = sum(this.zjinfos, val);
					this.form.other = sum(this.otherType, val)
				} else if (this.comtype == 1) {
					this.zjinfos.push(val);
					this.form.ysyongjin = sum(this.zjinfos, val);
					this.form.other = sum(this.otherType, val)
				} else { //改变比例及金额
					this.outstandingList.push(val);
					let reasonId = val.reasonId; ///本次理由
					let arr = this.outstandingList;
					arr.forEach(function(item) {
						if (item.reasonId == reasonId) {
							item.scale = val.scale
							item.outs = val.outs
						}
					})
					this.outstandingList = arr;
				}
			},
			click(index, index1) {
				if (index1 == 0) {
					this.outstandingList.splice(index, 1);
					unit.getArrayOutstandingList(this.outstandingList);
					unit.scale();
					this.outstandingList = unit.performance(this.form.ysyongjin);
					this.$u.toast(`已删除`);
				}
			},
			// 如果打开一个的时候，不需要关闭其他，则无需实现本方法
			open(index) {
				// 先将正在被操作的swipeAction标记为打开状态，否则由于props的特性限制，
				// 原本为'false'，再次设置为'false'会无效
				this.outstandingList[index].show = true;
				this.outstandingList.map((val, idx) => {
					if (index != idx) this.outstandingList[idx].show = false;
				})
			},
			click1(index, index1) {
				if (index1 == 0) {
					this.form.other = this.form.other - this.otherType[index].price //其他费用计算***金融
					this.otherType.splice(index, 1); //从数组中删除

					this.$u.toast(`已删除`);
				}
			},
			open1(index) {
				this.otherType[index].show = true;
				this.otherType.map((val, idx) => {
					if (index != idx) this.otherType[idx].show = false;
				})
			},
			click2(index, index1) { //中介
				if (index1 == 0) {
					this.form.ysyongjin = this.form.ysyongjin - this.zjinfos[index].price //其他费用计算//业绩 ** 中介
					this.zjinfos.splice(index, 1); //从数组中删除
					this.$u.toast(`已删除`);
				}
			},
			// 如果打开一个的时候，不需要关闭其他，则无需实现本方法
			open2(index) {
				// 先将正在被操作的swipeAction标记为打开状态，否则由于props的特性限制，
				// 原本为'false'，再次设置为'false'会无效
				this.zjinfos[index].show = true;
				this.zjinfos.map((val, idx) => {
					if (index != idx) this.zjinfos[idx].show = false;
				})
			},
			init() {
				this.$tool.uniRequest({
					url: "fangyuan/addesfy",
					method: 'GET',
					success: (res) => {
						this.formSelectInfo = res
						this.formSelectInfo.fwleixing.forEach(item => {
							item.value = item.xsshunxu
							item.label = item.lxming
						})
						this.formSelectInfo.xiaoqu.forEach(item => {
							item.value = item.id
							item.label = item.xiaoqum
						})
					}
				})
			},
			tagChange(val) {
				this.currentIndex = val
				this.form.leixing = val + 1
			},
			confirm1(e) {
				this.form.fwleixingLabel = e[0].label
				this.form.fwleixingValue = e[0].value
			},
			confirm2(e) {
				this.form.xiaoquLabel = e[0].label
				this.form.xiaoquValue = e[0].value
			},
			change(index) {
				this.current = index;
				this.isDisable = this.current === 1 ? true : false

			},
			showSelect(type) {
				if (this.isDisable) return
				this[type] = true
			},
			calendarChange(e) {
				this.form.cjtime = e.result
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
				let params = this.$u.deepClone(this.form)

				if (this.leixing === '售单') {
					params.leixing = 1
				} else if (this.leixing === '租单') {
					params.leixing = 2
				} else if (this.leixing === '新盘') {
					params.leixing = 3
				}
				params.yjinfos = JSON.stringify(this.otherType);
				params.fcinfos = JSON.stringify(this.outstandingList);
				params.zjinfos = JSON.stringify(this.zjinfos);
				console.log(params)
				this.$tool.uniRequest({
					url: "fangyuan/addesfy",
					method: 'POST',
					params,
					success: (res) => {
						this.$tool.uniShowToast({
							title: "添加成功"
						})
						this.$tool.uniRedirectTo({
							url: '/pages/home/reimbursement/index'
						})
					}
				})
			}
		}
	}
</script>

<style lang="scss" scoped>
	* {
		font-size: 30rpx;
	}

	.btnl {
		border: none;
		width: 520rpx;
		height: 60rpx;
	}

	.tit {
		width: 100%;
		padding: 30rpx 150rpx;
		background: #19be6b;
		color: #fff;
	}

	.listRed {
		padding: 20rpx;
		border-bottom: 1px solid red;
		margin: 0 20rpx;
	}

	.listBlue {
		padding: 20rpx;
		border-bottom: 1px solid blue;
		margin: 0 20rpx;
	}

	.tab {
		margin: 20rpx 0;
		background: #fff;
	}

	.form {
		border-radius: 20rpx;
		overflow: hidden;
	}

	.wrap {
		padding: 20rpx;
		background: #F8F8F8;

		.title {
			font-size: 32rpx;
			color: #767676;
		}

		.main {
			background-color: #F8F8F8;
			margin: auto;
			padding-top: 30rpx;

			.flex {
				display: flex;
				justify-content: space-between;
				align-items: center;
			}

			.radio {
				width: 100%;
				padding: 30rpx;
			}

			.bg {
				background: #fff;
				padding-left: 20rpx;

				.fx {
					display: flex;
					padding: 20rpx 10rpx;

				}

				.inline {

					.input {
						padding-left: 10rpx;
						justify-content: space-around;

						.item {
							width: 25%;
							flex: 0 0 auto;
						}

						.item2 {
							width: 21%;
							margin: 0 10rpx;
							flex: 0 0 auto;
							text-align: center;
						}
					}
				}

				.label {
					width: 70px;
					text-align: right;
				}

				.textarea {
					padding: 20rpx 30rpx;
				}
			}

			.red {
				color: red;
			}

			.margin-top {
				margin-top: 30rpx;
			}

			.margin-bottom {
				margin-bottom: 30rpx;
			}

			.steps {
				padding: 30rpx;

				.small-title {}
			}

			.btn {
				width: 80%;
				margin: 20rpx auto;

				.bt {
					background-color: #07c160;
					color: #fff;
				}
			}
		}
	}
</style>
