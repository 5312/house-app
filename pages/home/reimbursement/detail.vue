<template>
	<view class="wrap">
		<a-navbar title="报单详情" @back="$tool.uniRedirectTo({url:'/pages/home/reimbursement/index'})"></a-navbar>	
		<view class="bg title">基本信息</view>
		<u-form class="form" v-if="detail">
			<u-form-item class="bg" label-align="right" label="成交类型:"  :label-style="color" label-width='150'>
				<u-input type="text" :disabled="isDisable" placeholder="" v-model="detail.typename"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="合同编号:" :label-style="color" label-width='150'>
				<u-input type="text" :disabled="isDisable" placeholder="" v-model="detail.htbianhao"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="成交人:"  :label-style="color" label-width='120'>
				<u-input type="text" :disabled="isDisable" placeholder="" v-model="detail.cjname"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="成交时间:" :label-style="color"  label-width='150'>
				<u-input type="text" :disabled="isDisable" placeholder="" v-model="detail.cjtime"></u-input>
			</u-form-item>
			<view class="detail">房屋信息</view>
			<u-form-item class="bg" label-align="right" label="房屋地址:" :label-style="color"  label-width='150'>
				<u-input type="text" :disabled="isDisable" placeholder="" v-model="detail.dizhi"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="户型:"  :label-style="color" label-width='100'>
				<u-input type="text" :disabled="isDisable" placeholder="" v-model="detail.huxing"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="面积:" :label-style="color"  label-width='100'>
				<u-input type="text" :disabled="isDisable" placeholder="" v-model="detail.mianji"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="楼层:"  :label-style="color" label-width='100'>
				<u-input type="text" :disabled="isDisable" placeholder="" v-model="detail.louceng"></u-input>
			</u-form-item>
			<!--  -->
			<view class="detail">合同信息</view>
			<u-form-item class="bg" label-align="right" label="甲方:" :label-style="color"  label-width='100'>
				<u-input type="text" :disabled="isDisable" placeholder="" v-model="detail.yzname"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="联系方式:" :label-style="color"  label-width='150'>
				<u-input type="text" :disabled="isDisable" placeholder="" v-model="detail.yzdianhua"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="乙方:" :label-style="color"  label-width='100'>
				<u-input type="text" :disabled="isDisable" placeholder="" v-model="detail.khname"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="联系方式:" :label-style="color" label-width='150'>
				<u-input type="text" :disabled="isDisable" placeholder="" v-model="detail.khdianhua"></u-input>
			</u-form-item>
			<view class="detail">成交信息</view>
			<u-form-item class="bg" label-align="right" label="成交价格:"  :label-style="color" label-width='150'>
				<u-input type="text" :disabled="isDisable" placeholder="" v-model="detail.cjjiage"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="应收佣金:" :label-style="color"  label-width='150'>
				<u-input type="text" :disabled="isDisable" placeholder="" v-model="detail.ysyongjin"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="中介类应收佣金:" :label-style="color"  label-width='240'>
				<u-input type="text" :disabled="isDisable" placeholder="" v-model="detail.zjyongjin"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="金融类应收佣金:" :label-style="color"  label-width='240'>
				<u-input type="text" :disabled="isDisable" placeholder="" v-model="detail.jryongjin"></u-input>
			</u-form-item>
			<view class="detail">补充条款</view>
			<view class="bg ta">
				<u-input type="textarea " height="200" :disabled="isDisable" v-model="detail.beizhu"></u-input>
			</view>
			<view class='rim'>业绩信息</view>
			<u-form-item class="bg" label-align="right" label="业绩:" :label-style="color"  label-width='100'>
				<u-input type="text" :disabled="isDisable" placeholder="" v-model="detail.fczhuangtai"></u-input>
			</u-form-item>
			<u-form-item class="bg" label-align="right" label="中介类佣金:" :label-style="color"  label-width='180'>
				<u-input type="text" :disabled="isDisable" placeholder="" v-model="detail.yongjing"></u-input>
			</u-form-item>
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
		</u-form>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				detail:null,
				isDisable:true,
				th:{
					fontWeight:'600'
				},
				color:{
					color:'#A1A1A1'
				},

			}
		},
		onLoad(options){
			options.id && this.getDetail(options.id)
		},
		methods: {
			getDetail(id){
				this.$tool.uniRequest({
					url:"chengjiao/neirong/",
					method:'GET',
					params:{
						id
					},
					success:(res)=>{
						this.detail=res
					}
				})
			}
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
			background: $bg;
			margin-bottom: 20rpx;
			color:#19be6b;
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
