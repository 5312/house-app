<template>
	<view class="wrap">
		<a-navbar title="报单详情" @back="$tool.uniRedirectTo({url:'/pages/home/reimbursement/index'})"></a-navbar>
		<view class="bg title">基本信息</view>
		<u-form class="form" v-if="detail">
			<u-form-item class="bg" label-align="right" label="成交类型:" :label-style="color" label-width='150'>
				<u-input type="text" :disabled="isDisable" placeholder="" v-model="detail.typename"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="合同编号:" :label-style="color" label-width='150'>
				<u-input type="text" :disabled="isDisable" placeholder="" v-model="detail.htbianhao"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="房屋地址:" :label-style="color" label-width='150'>
				<u-input type="text" :disabled="isDisable" placeholder="" v-model="detail.dizhi"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="成交人:" :label-style="color" label-width='120'>
				<u-input type="text" :disabled="isDisable" placeholder="" v-model="detail.cjname"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="成交时间:" :label-style="color" label-width='150'>
				<u-input type="text" :disabled="isDisable" placeholder="" v-model="detail.cjtime"></u-input>
			</u-form-item>
			<!-- 	<view class="detail">房屋信息</view>
			
			<u-form-item class="bg" label-align="right" label="户型:"  :label-style="color" label-width='100'>
				<u-input type="text" :disabled="isDisable" placeholder="" v-model="detail.huxing"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="面积:" :label-style="color"  label-width='100'>
				<u-input type="text" :disabled="isDisable" placeholder="" v-model="detail.mianji"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="楼层:"  :label-style="color" label-width='100'>
				<u-input type="text" :disabled="isDisable" placeholder="" v-model="detail.louceng"></u-input>
			</u-form-item> -->
			<!--  -->
			<view class="detail">合同信息</view>
			<u-form-item class="bg" label-align="right" label="甲方:" :label-style="color" label-width='100'>
				<u-input type="text" :disabled="isDisable" placeholder="" v-model="detail.yzname"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="联系方式:" :label-style="color" label-width='150'>
				<u-input type="text" :disabled="isDisable" placeholder="" v-model="detail.yzdianhua"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="乙方:" :label-style="color" label-width='100'>
				<u-input type="text" :disabled="isDisable" placeholder="" v-model="detail.khname"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="联系方式:" :label-style="color" label-width='150'>
				<u-input type="text" :disabled="isDisable" placeholder="" v-model="detail.khdianhua"></u-input>
			</u-form-item>
			<view class="detail">成交信息</view>
			<u-form-item class="bg" label-align="right" label="成交价格:" :label-style="color" label-width='150'>
				<u-input type="text" :disabled="isDisable" placeholder="" v-model="detail.cjjiage"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="应收佣金:" :label-style="color" label-width='150'>
				<u-input type="text" :disabled="isDisable" placeholder="" v-model="detail.ysyongjin"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="其他费用:" :label-style="color" label-width='150'>
				<u-input type="text" :disabled="isDisable" placeholder="" v-model="detail.zjyongjin"></u-input>
			</u-form-item>
			<!-- <u-form-item class="bg" label-align="right" label="中介类应收佣金:" :label-style="color" label-width='240'>
				<u-input type="text" :disabled="isDisable" placeholder="" v-model="detail.zjyongjin"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="金融类应收佣金:" :label-style="color" label-width='240'>
				<u-input type="text" :disabled="isDisable" placeholder="" v-model="detail.jryongjin"></u-input>
			</u-form-item> -->
			<view class="detail">补充条款</view>
			<view class="bg ta">
				<u-input type="textarea " height="200" :disabled="isDisable" v-model="detail.beizhu"></u-input>
			</view>
			<!-- <view class='rim'>业绩信息</view>
			<u-form-item class="bg" label-align="right" label="业绩:" :label-style="color"  label-width='100'>
				<u-input type="text" :disabled="isDisable" placeholder="" v-model="detail.fczhuangtai"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="中介类佣金:" :label-style="color"  label-width='180'>
				<u-input type="text" :disabled="isDisable" placeholder="" v-model="detail.yongjing"></u-input>
			</u-form-item> -->
			<view class="detail">中介类业绩信息</view>
			<view class="bg ta" v-if="detail.fencheng">
				<u-table :th-style="th">
					<u-tr>
						<u-td>分成人</u-td>
						<u-td>业绩缘由</u-td>
						<u-td>业绩比例</u-td>
						<u-td>业绩金额</u-td>
						<u-td>实收金额</u-td>
					</u-tr>
					<u-tr v-for="(x,index) in detail.fencheng " :key='index'>
						<u-td>{{x.fcname}}</u-td>
						<u-td>{{x.fcyuanyou}}</u-td>
						<u-td>{{x.fcbl}}</u-td>
						<u-td>{{x.fcyj}}</u-td>
						<u-td>{{x.ssyj}}</u-td>
					</u-tr>
				</u-table>
			</view>
			<view class="bottoms">
				<view class='titles'>流程</view>
				<view v-for="(x,y) in numList" :key='y' class="">
					<view class="flex a-center lis">
						<view class="imageCircle">
							{{x.xingming || ''}}
							<u-icon v-if="x.sh_type == 1 && x.bohui!=1" name="checkbox-mark" class="icon success" color="#fff"></u-icon>
							<u-icon v-if='x.bohui == 1' name='more-dot-fill' class='icon warning' color="#fff"></u-icon>
						</view>
						<view class="center fixheig">
							<view class="titles">{{x.leixing}}</view>
							<view class="tl" :class="{ bohui:x.bohui == 1 }" v-if="x.bohui != 1">{{ x.msg == "0" ? '': x.msg ? x.msg :'（未审批）'}}</view>
							<view class="tl" :class="{ bohui:x.bohui == 1 }" v-if="x.bohui == 1">{{x.msg}}</view>
						</view>
						<view class="fixheig col">{{x.addtime == 0 ? '':x.addtime}}</view>
					</view>
					<view v-if="y != numList.length-1" class="line"></view>
				</view>
			</view>
			<view class="btnq">
				<u-button type="success" v-if="sh_type <= 2" @click="show = true">撤回申请</u-button>
			</view>
			<u-modal v-model="show" mode="center" @confirm="confirm" @cancel="cancel" :mask-close-able="true" title=""
			 :show-cancel-button="true">
				<view class="t"> 是否撤回？</view>
			</u-modal>
		</u-form>
	</view>
</template>

<script>
	import api from "@/utils/api/info.js"
	export default {
		data() {
			return {
				detail: null,
				show: false,
				sh_type: 0,
				isDisable: true,
				th: {
					fontWeight: '600'
				},
				color: {
					color: '#A1A1A1'
				},
				numList: [{
						name: '发起申请'
					},
					{
						name: '审批人'
					}
				],
			}
		},
		onLoad(options) {
			options.id && this.getDetail(options.id)

		},
		methods: {
			getDetail(id) {
				this.$tool.uniRequest({
					url: "chengjiao/neirong/",
					method: 'GET',
					params: {
						id
					},
					success: (res) => {
						this.detail = res
						this.numList = res.shenpi;
						this.sh_type = res.cjtype;
					}
				})
			},
			confirm() { //确认
				let _this = this
				this.$tool.uniRequest({
					url: 'chengjiao/backsp/',
					method: 'GET',
					params: {
						id: this.id,
						cjbianhao:this.detail.cjbianhao
					},
					success: function(res, msg) {

						_this.$tool.uniShowToast({
							title: msg.msg
						})
						_this.$tool.uniSwitchTab({
							url: `/pages/home/index`
						})
					}
				})

			},
		}
	}
</script>

<style lang="scss" scoped>
	$bg:#F2F2F2;

	.inline {
		display: inline-block;
		vertical-align: middle;
	}

	.col {
		color: #888888;
	}
	.t{
		text-align: center;
	}
	.warning {
		background-color: #FD9640;
	}

	.bohui {
		color: #FD9640;
	}

	.success {
		background-color: #08C081;
	}

	.icon {
		position: absolute;
		right: -8rpx;
		bottom: 10rpx;
		border-radius: 50%;
		/* box-shadow: 1px 1px 1px 1px #ddd; */
	}

	.fixheig {
		height: 100rpx;
		margin: 30rpx 0;
		white-space: nowrap;
		text-align: right;
		width: 200rpx;
		overflow: hidden;
	}

	.height {
		height: 480rpx;
		overflow: auto;
	}

	.btnq {
		width: 85%;
		margin: 0rpx auto;
		padding: 0 0 100rpx;
	}

	label {
		color: #ddd;
	}

	.titles {
		font-size: 32rpx;
		text-align: left;
	}

	.bottom {
		margin-bottom: 100rpx;
	}

	.bottoms {
		margin: 80rpx 40rpx;
		background-color: #fff;
		padding: 20rpx;
		border-radius: 20rpx;
	}

	.imageCircle {
		width: 100rpx;
		height: 100rpx;
		border-radius: 50%;
		background: #3195FE;
		text-align: center;
		line-height: 100rpx;
		position: relative;
		color: #fff;
		margin: 30rpx;
	}

	.center {
		line-height: 50rpx;
		margin-right: 80rpx;
	}

	.line {
		border: 1px solid #ddd;
		width: 65rpx;
		position: relative;
		left: 50rpx;
		transform: rotate(90deg);
	}

	.tl {
		text-align: left;
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
			background: $bg;
			margin-bottom: 20rpx;
			color: #19be6b;
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
