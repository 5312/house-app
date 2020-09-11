<template>
	<view class="wrap">
		<view class="bg title">基本信息</view>
		<u-form class="form">
			<u-form-item class="bg" label-align="right" label="成交类型:" :label-style="color" label-width='150'>
				<u-input type="text" disabled="false" v-model="l1"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="合同编号:" :label-style="color" label-width='150'>
				<u-input type="text" disabled="false" v-model="value"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="成交人:"  :label-style="color" label-width='120'>
				<u-input type="text" disabled="false" v-model="value"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="成交时间:" :label-style="color"  label-width='150'>
				<u-input type="text" disabled="false" v-model="value"></u-input>
			</u-form-item>
			<view class="detail">房屋信息</view>
			<u-form-item class="bg" label-align="right" label="房屋地址:" :label-style="color"  label-width='150'>
				<u-input type="text" disabled="false" v-model="value"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="户型:"  :label-style="color" label-width='100'>
				<u-input type="text" disabled="false" v-model="value"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="面积:" :label-style="color"  label-width='100'>
				<u-input type="text" disabled="false" v-model="value"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="楼层:"  :label-style="color" label-width='100'>
				<u-input type="text" disabled="false" v-model="value"></u-input>
			</u-form-item>
			<!--  -->
			<view class="detail">合同信息</view>
			<u-form-item class="bg" label-align="right" label="甲方:" :label-style="color"  label-width='100'>
				<u-input type="text" disabled="false" v-model="value"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="联系方式:" :label-style="color"  label-width='150'>
				<u-input type="text" disabled="false" v-model="value"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="乙方:" :label-style="color"  label-width='100'>
				<u-input type="text" disabled="false" v-model="value"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="联系方式:" :label-style="color" label-width='150'>
				<u-input type="text" disabled="false" v-model="value"></u-input>
			</u-form-item>
			<view class="detail">成交信息</view>
			<u-form-item class="bg" label-align="right" label="成交价格:"  :label-style="color" label-width='150'>
				<u-input type="text" disabled="false" v-model="value"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="应收佣金:" :label-style="color"  label-width='150'>
				<u-input type="text" disabled="false" v-model="value"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="中介类应收佣金:" :label-style="color"  label-width='240'>
				<u-input type="text" disabled="false" v-model="value"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="金融类应收佣金:" :label-style="color"  label-width='240'>
				<u-input type="text" disabled="false" v-model="value"></u-input>
			</u-form-item>
			<view class="detail">补充条款</view>
			<view class="bg ta">
				<u-input type="textarea " height="200" disabled="false" v-model="value"></u-input>
			</view>
			<view class='rim'>业绩信息</view>
			<u-form-item class="bg" label-align="right" label="业绩:" :label-style="color"  label-width='100'>
				<u-input type="text" disabled="false" v-model="value"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="中介类佣金:" :label-style="color"  label-width='180'>
				<u-input type="text" disabled="false" v-model="value"></u-input>
			</u-form-item>
			<view class="detail">中介类业绩信息</view>
			<view class="bg ta">
				<u-table :th-style="th">
					<u-tr>
						<u-td>分成人</u-td>
						<u-td>业绩缘由</u-td>
						<u-td>业绩比例</u-td>
						<u-td>业绩金额</u-td>
						<u-td>实收金额</u-td>
					</u-tr>
					<u-tr v-for="(x,index) in lists " :key='index'>
						<u-td>{{x.name}}</u-td>
						<u-td>{{x.yuan}}</u-td>
						<u-td>{{x.bili}}</u-td>
						<u-td>{{x.price}}</u-td>
						<u-td>{{x.actual}}</u-td>
					</u-tr>
				</u-table>
			</view>
		</u-form>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				th:{
					fontWeight:'600'
				},
				color:{
					color:'#A1A1A1'
				},
				l1: '租赁',
				value: '暂无数据',
				lists: [{
					name: '刘金莉',
					yuan: '合同成交',
					bili: '90.00%',
					price: 72.00,
					actual: '0.00 元'
				}],
				list: [{
					name: '刘达明',
					tel: '1333333333',
					de: 'Adaing'
				}, {
					name: '刘达明',
					tel: '1333333333',
					de: 'Adaing'
				}, {
					name: '刘达明',
					tel: '1333333333',
					de: 'Adaing'
				}, {
					name: '刘达明',
					tel: '1333333333',
					de: 'Adaing'
				}],
			}
		},
		methods: {

		}
	}
</script>

<style lang="scss" scoped>
	$bg:#F2F2F2;

	.inline {
		display: inline-block;
		vertical-align: middle;
	}

	.height {
		height: 480rpx;
		overflow: auto;
	}
	label{
		color:#ddd;
	}
	.bottom {
		margin-bottom: 100rpx;
	}

	.bg {
		background-color: #fff;

		.right {
			width: calc(100% - 150px);
			margin-left: 20rpx;

			.tit {
				font-size: 32rpx;
				font-weight: 600;
			}

			.bott {
				font-size: 20rpx;
			}
		}

		.item {
			margin: 20rpx;
		}

		.inlinebox {
			height: 370rpx;
			white-space: nowrap;
			overflow-x: auto;

			.img {
				display: inline-block;
				vertical-align: middle;
				padding: 20rpx;
			}
		}
	}

	.wrap {
		background-color: $bg;

		.title {
			padding: 20rpx;
			background: #fff;
			margin-bottom: 20rpx;
			color: #999999;
		}

		.detail {
			@extend .title;
			margin: 10rpx 0;
		}

		.rim {
			@extend .detail;
		}

		.ta {
			padding: 20rpx;
		}
	}

	.btn {
		position: fixed;
		bottom: 0rpx;
		z-index: 9;
		width: 100%;
		text-align: center;
		background: #fff;

		.b {
			width: 35%;
			display: inline-block;
			margin: 10rpx 20rpx;
		}
	}
</style>
