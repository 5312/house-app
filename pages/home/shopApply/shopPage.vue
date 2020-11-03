<template>
	<view class="shop-transfer-application">
		<a-navbar title="员工开店申请" @back="$tool.uniSwitchTab({url:'/pages/home/index'})"></a-navbar>
		<view class="content">
			<u-form :model="form" ref="uForm" label-width='140rpx' class="form form-top" label-position='top' v-if='form'
			 :error-type="errorType">
				<!--  -->
				<view class="title bold u-border-bottom ">
					<u-icon size="28" name="calendar" class="mar"></u-icon><text>操作信息</text>
				</view>
				<u-form-item label="开店地址" :required="true" prop='d_dizhi'>
					<u-input v-model="form.d_dizhi" placeholder="请输入开店地址" type="text" />
				</u-form-item>
				<u-form-item label="房租" :required="true" prop='d_fangzu'>
					<u-input v-model="form.d_fangzu" placeholder="请输入房租" type="number" />
				</u-form-item>
				<u-form-item label="投资金额" :required="true" prop='d_touzu'>
					<u-input v-model="form.d_touzi" placeholder="请输入投资金额" type="number" />
				</u-form-item>
				<u-form-item label="押金" :required="true" prop='d_yajin'>
					<u-input v-model="form.d_yajin" placeholder="请输入押金" type="number" />
				</u-form-item>
				<u-form-item label="付款方式" :required="true" prop="d_fukuan">
					<u-input v-model="form.d_fukuanLabel" placeholder="请选择付款方式" type="select" @click="actionSheet('d_fukuan')" />
					<u-select v-model="d_fukuan" :list="fuKuan" @confirm="confirm1"></u-select>
				</u-form-item>
				<u-form-item label="开店类型" :required="true" prop='d_type'>
					<u-input v-model="form.d_typeLabel" placeholder="请选择开店类型" type="select" @click="actionSheet('d_type')" />
					<u-select v-model="d_type" :list="type" @confirm="confirm2"></u-select>

				</u-form-item>
				<u-form-item label="组数" :required="true" prop="d_zu">
					<u-input v-model="form.d_zu" placeholder="请输入组数" type="number" />
				</u-form-item>
				<u-form-item label="人数" :required="true" prop="d_ren">
					<u-input v-model="form.d_ren" type='number' />
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
				errorType: ['message'],
				d_type: false,
				d_fukuan: false,
				thisId:null,
				fuKuan: [ //1季付  1半年  2年付  
					{
						label: '季付',
						value: 1
					},
					{
						label: '半年',
						value: 2
					},
					{
						label: '年付',
						value: 3
					}
				],
				type: [{
						label: '直营店',
						value: 1
					}, {
						label: 'A店 ',
						value: 2
					},
					{
						label: 'A店中店 ',
						value: 3
					},
					{
						label: '外展店 ',
						value: 4
					}
				],
				form: {
					d_dizhi: '',
					d_fangzu: '',
					d_touzu: '',
					d_yajin: '',
					d_fukuan: '',
					d_type: '',
					d_zu: '',
					d_ren: ''
				},
				rules: {
					d_dizhi: [ // 必填规则
						{
							required: true,
							message: '请输入开店地址',
							trigger: ['blur', 'change'],
						},
					],
					d_fangzu: [ // 必填规则
						{
							required: true,
							message: '请输入房租',
							trigger: ['blur', 'change'],
						},
					],
					d_touzi: [ // 必填规则
						{
							required: true,
							message: '请输入投资金额',
							trigger: ['blur', 'change'],
						},
					],
					d_yajin: [ // 必填规则
						{
							required: true,
							message: '请输入押金',
							trigger: ['blur', 'change'],
						},
					],
					/**/
					d_fukuan: [{
						required: true,
						message: '请选择付款方式',
						trigger: ['change'],
						validator: (rule, value, callback) => {
							console.log( value)
							return true;
						},
					}, ],
					d_type: [ // 必填规则
						{
							required: true,
							message: '请选择开店类型',
							trigger: ['change'],
							validator: (rule, value, callback) => {
								console.log( value)
								return true;
							},
						},
					],
					d_zu: [ // 必填规则
						{
							required: true,
							message: '请输入数组',
							trigger: ['blur', 'change'],
						},
					],
					d_ren: [ // 必填规则
						{
							required: true,
							message: '请输入人数',
							trigger: ['blur', 'change'],
						},
					],
				},
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
			actionSheet(type) {
				this[type] = true
			},
			getDetail() {
				let _this = this;
				let data = {};
				api.dianApplication(data,'GET').then(res => {
					console.log(res)
					setTimeout(() => {
						this.$tool.uniRedirectTo({
							url: `/pages/home/shopApply/detail?id=${res.id}`
						})
					}, 1000)
				})
			},
			confirm1(e) {
				this.form.d_fukuan = e[0].value; //对应的数字
				this.form.d_fukuanLabel = e[0].label; //对应的数字
			},
			confirm2(e) {
				this.form.d_type = e[0].value; //对应的数字
				this.form.d_typeLabel = e[0].label; //对应的数字
			},
			submit() {
				let data = this.form;
				let iThis = this;
				console.log(data)
				api.dianApplication(data,'POST').then(res => {
					iThis.thisId= res.id;
					this.$tool.uniShowToast({
						title: "开店申请成功！"
					})
					setTimeout(() => {
						this.$tool.uniRedirectTo({
							url: `/pages/home/shopApply/detail?id=${res.id}`
						})
					}, 1000)
				})
			},
			validataSublime() {
				this.$refs.uForm.validate(valid => {
					if (valid) {
						console.log('验证成功')
						this.submit();
					} else {
						console.log('验证失败');
					}
				});
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
