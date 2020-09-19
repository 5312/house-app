<template>
	<view>	
		<a-navbar title="新增报账" @back="$tool.uniRedirectTo({url:'/pages/home/reimbursement/index'})"></a-navbar>
		<u-tabs :list="lists" active-color='#07c160' inactive-color='#000' :is-scroll="false" :current="current" @change="change"></u-tabs>

		<view class="wrap">
		<!-- 	<u-subsection :list="leixingList" :current="currentIndex" @change='tagChange'></u-subsection> -->
			<!-- <view class="title">第一步:基本信息</view> -->
			<view class="main">
				<u-form class="form" :model="form" ref="uForm">
					<u-form-item class="bg" label-width='150' label-align='rigth' label="报单类型:">
						<view>{{leixing}}</view>
					</u-form-item>
					<u-form-item class="bg" label-width='150' label-align='rigth' label="房屋地址:">
						<u-input v-model="form.fdizhi" :disabled="isDisable" />
					</u-form-item>
					<u-form-item class="bg" label-width='150' label-align='rigth' label="房屋类型:" v-if='formSelectInfo'>
						<u-input :selectOpen="fangType" placeholder="" type="select" v-model="form.fwleixingLabel" @click='showSelect("fangType")' />
						<u-select v-model="fangType" :list="formSelectInfo.fwleixing" @confirm="confirm1"></u-select>
					</u-form-item>
					<u-form-item class="bg" label-width='150' label-align='rigth' label="所属小区:" v-if='formSelectInfo'>
						<u-input :selectOpen="xiaoqu" placeholder="" type="select" v-model="form.xiaoquLabel" @click='showSelect("xiaoqu")' />
						<u-select v-model="xiaoqu" :list='formSelectInfo.xiaoqu' @confirm="confirm2"></u-select>
					</u-form-item>
					<u-form-item class="bg" label-width='150' label-align='rigth' label="使用面积:">
						<u-input v-model="form.symianji" :disabled="isDisable" />
					</u-form-item>
					<u-form-item class="bg " label-width='150' label-align='rigth' label="门牌号">
						<view class="inline">
							<view class="flex  input">
								<u-input class="item" :border='true' :disabled="isDisable" v-model.number="form.zuodong" placeholder='栋' :type="type" />
								<u-input class="item" :border='true' :disabled="isDisable" v-model.number="form.danyuan" placeholder='单元' :type="type" />
								<u-input class="item" :border='true' :disabled="isDisable" v-model.number="form.fanghao" placeholder='房号' :type="type" />
							</view>
						</view>
					</u-form-item>
					<u-form-item class="bg" label-width='150' label-align='rigth' label="户型">
						<view class="inline">
							<view class="flex  input">
								<u-input class="item2" :border='true' :disabled="isDisable" v-model.number="form.shi" placeholder='室' :type="type" />
								<u-input class="item2" :border='true' :disabled="isDisable" v-model.number="form.ting" placeholder='厅' :type="type" />
								<u-input class="item2" :border='true' :disabled="isDisable" v-model.number="form.wei" placeholder='卫' :type="type" />
								<u-input class="item2" :border='true' :disabled="isDisable" v-model.number="form.yangtai" placeholder='阳台'
								 :type="type" />
							</view>
						</view>
					</u-form-item>
					<u-form-item class="bg" label-width='150' label-align='rigth' label="业主姓名:">
						<u-input v-model="form.yezhu" :disabled="isDisable" />
					</u-form-item>
					<u-form-item class="bg" label-width='150' label-align='rigth' label="业主电话:">
						<u-input v-model="form.yezhudianhua" :disabled="isDisable" />
					</u-form-item>
					<u-form-item class="bg" label-width='150' label-align='rigth' label="业主性别:">
						<u-radio-group class="bg  flex" v-model="form.ezhulx" :disabled="isDisable">
							<u-radio v-for="(item, index) in listSex" :key="index" :name="item.value" :disabled="item.disabled">
								{{item.name}}
							</u-radio>
						</u-radio-group>
					</u-form-item>
					<u-form-item class="bg" label-width='150' label-align='rigth' label="客户姓名:">
						<u-input v-model="form.khxingming" :disabled="isDisable" />
					</u-form-item>
					<u-form-item class="bg" label-width='150' label-align='rigth' label="客户电话:">
						<u-input v-model="form.dianhua" :disabled="isDisable" />
					</u-form-item>
					<u-form-item class="bg" label-width='150' label-align='rigth' label="客户性别:">
						<u-radio-group class="bg  flex" v-model="form.kehulx" :disabled="isDisable">
							<u-radio v-for="(item, index) in listSex" :key="index" :name="item.value" :disabled="item.disabled">
								{{item.name}}
							</u-radio>
						</u-radio-group>
					</u-form-item>
					<u-form-item class="bg" label-width='150' label-align='rigth' label="成交时间:">
						<u-input :selectOpen="show" placeholder="" v-model="form.cjtime" type="select" @click='showSelect("show")' />
						<u-calendar max-date='5000' v-model="show" mode="date" @change="calendarChange"></u-calendar>
					</u-form-item>
					<u-form-item class="bg" label-width='150' label-align='rigth' label="成交价格:">
						<u-input v-model="form.cjjiage" placeholder='xxx元' :disabled="isDisable" />
					</u-form-item>
					<u-form-item class="bg" label-width='150' label-align='rigth' label="成交业绩:">
						<u-input v-model="form.ysyongjin" placeholder='xxx元' :disabled="isDisable" />
					</u-form-item>
					<u-form-item class="bg" label-width='150' label-align='rigth' label="备注:">
						<u-input type='textarea' v-model="form.beizhu" placeholder="" :disabled="isDisable" />
					</u-form-item>

					<view class="steps bg margin-bottom margin-top">
						<view class="small-title">审批流程</view>
						<u-steps direction='column' mode="dot" :list="numList" :current="0"></u-steps>
					</view>
					<view class="btn">
						<button class="bt" @click="submit">提交</button>
					</view>
				</u-form>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {			
				isDisable: false,
				fangType: false,
				xiaoqu: false,
				show: false,
				formSelectInfo: null,
				currentIndex: 0,
				leixing:null,
				leixingList: [{
						name: '售单'
					},
					{
						name: '租单'
					}, {
						name: '新盘'
					}
				],
				form: {
					leixing: null,
					fwleixingLabel: '',
					fwleixingValue: '',
					fdizhi: null,
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
					ysyongjin: '',
					beizhu: ''
				},
				lists: [{
						name: '发起提交'
					},
					{
						name: '查看数据'
					}
				],
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
		onLoad(options) {
			this.leixing=options.type
			this.init()
		},
		methods: {
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
			submit() {
				let params = this.$u.deepClone(this.form)
				console.log(this.leixing)
				if(this.leixing==='售单'){
					params.leixing=1
				}else if(this.leixing==='租单'){
					params.leixing=2
				}else if(this.leixing==='新盘'){
					params.leixing=3
				}
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
	.tit{
		width:100%;
		padding:30rpx 150rpx;
		background: #19be6b;
		color:#fff;
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

				.fx {
					display: flex;
					padding: 20rpx 10rpx;

				}

				.inline {

					/* 	width: calc(100% - 70px); */
					/* margin: auto; */
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
