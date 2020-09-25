<template>
	<view class="body">
		<a-navbar title="详情" @back="$tool.uniNavigateTo({url:'/pages/approve/shenpi'})" background-color='#fff'></a-navbar>
		<view class="header">
			<view class="title">{{to_title}}</view>
			<view class="small">{{bumen}}</view>
			<view v-if="sh_zhuangtai == -1" class="info">审批被拒绝</view>
			<view v-else class="status">等{{sbr}}处理</view>
		</view>
		<view class="wrap" v-if="neirong">
			<view v-html="neirong"></view>
		</view>
		<u-empty v-else text="数据为空" class="empty" mode="list"></u-empty>
		<view class="bottom">
			<view class='title'>流程</view>
			<view v-for="(x,y) in numList" :key='y' class="">
				<view class="flex a-center lis">
					<view class="imageCircle">
						{{x.xingming}}
						<u-icon v-if="x.sh_type == 1 && x.bohui!=1" name="checkbox-mark" class="icon success" color="#fff"></u-icon>
						<u-icon v-if='x.bohui == 1' name='more-dot-fill' class='icon warning' color="#fff"></u-icon>
					</view>
					<view class="center fixheig">
						<view class="title">{{x.leixing}}</view>
						<view class="tl" :class="{ bohui:x.bohui == 1 }" v-if="x.bohui != 1">{{x.msg == '0'?'':'（未审批）'}}</view>
						<view class="tl" :class="{ bohui:x.bohui == 1 }" v-if="x.bohui == 1">{{x.msg}}</view>
					</view>
					<view class=" fixheig col">{{x.addtime == 0 ? '':x.addtime}}</view>
				</view>
				<view v-if="y != numList.length-1" class="line"></view>
			</view>
		</view>
		<view v-if='current == 0 && sh_zhuangtai != 3' class="bb flex a-center j-between">
			<u-button type="warning" :ripple="true" @click="show = true;status=1;title='驳回理由'">拒绝</u-button>
			<u-button type="success" :ripple="true" @click="show = true;status=0;title='批示内容'">同意</u-button>
		</view>
		<u-modal v-model="show" mode="center" @confirm="confirm" @cancel="cancel" :mask-close-able="true" :title="title" :show-cancel-button="true">
			<u-field v-model="msg" label=""  label-width='0px'  placeholder="请审批意见" class="inp" type="textarea" :error-message='errmsg'>
			</u-field>
		</u-modal>
	</view>
</template>

<script>
	import api from "@/utils/api/info.js"
	export default {
		data() {
			return {
				title:'批示内容',
				show: false,
				current: '',
				status: '', //0同意or1驳回
				errmsg: '',
				msg: '',
				id: '',
				to_title: '',
				sbr: '',
				bumen: '',
				sh_zhuangtai: '',
				neirong: '',
				numList: [{
						name: '发起申请'
					},
					{
						name: '审批人'
					}
				]
			}
		},
		onLoad(option) {
			this.id = option.id; //id,请求参数
			this.current = option.current
			this.showData();//页面数据
		},
		methods: {
			confirm() { //确认\
				this.show = true;
				if (this.msg) {
					this.submitRequest(1);
					this.show = false;
					this.msg = '';
				} else {
					this.errmsg = '请输入批示'
				}
			},
			cancel(){
				this.msg = '';
			},
			submitRequest() {
				let data = {
					id: this.id,
					sh_zhuangtai: this.sh_zhuangtai, //当前状态
					sp_type: this.status, ///0同意1驳回
					msg: this.msg
				};
				let _this = this
				api.submitShenPi(data).then(res => {
					_this.show = false;
				});
				this.showData();//审批后刷新页面
			},
			showData() {
				let data = {
					id: this.id,
				}
				let _this = this;
				console.log('页面数据')
				api.shengPiXiangQing(data).then(result => {
					//console.log(result)
					_this.to_title = result.to_title;
					_this.bumen = result.bumen;
					_this.sbr = result.sbr;
					_this.neirong = result.neirong;
					_this.numList = result.list;
					_this.sh_zhuangtai = result.sh_zhuangtai //当前状态
				})
			}
		}
	}
</script>

<style lang="scss" scoped>
	.success {
		background-color: #08C081;
	}
	.info{
		color:#959595;
	}
	.empty{
		padding:50rpx 0;
	}
	.col{
		color:#888888;
	}
	.warning {
		background-color: #FD9640;
	}
	.bohui{
		color:#FD9640;
	}
	.fixheig {
		height: 100rpx;
		margin: 30rpx 0;
		white-space: nowrap;
		text-align: right;
		width:200rpx;
		overflow: hidden;
	}
	.tetxalign{
			text-align: left;
	}
	.tl{
		text-align: left;
	}
	page {
		.title {
			font-size: 32rpx;
			font-weight: 700;
			text-align: left;
		}

		.body {
			height: 100%;
			background-color: #EDEDED;
			padding-bottom: 100rpx;

			.header {
				padding: 20rpx;
				line-height: 50rpx;
				background: #fff;

				.small {
					color: #adadad;

				}

				.status {
					color: #D8A066;
				}
			}

			.wrap {
				margin: 80rpx 40rpx;
				background: #fff;
				padding: 20rpx;
				line-height: 80rpx;
			}

			.bottom {
				margin: 80rpx 40rpx;
				background-color: #fff;
				padding: 20rpx;
				border-radius: 20rpx;
			}

			.bb {
				width: 100%;
				padding: 20rpx;
				background-color: #fff;
				position: fixed;
				bottom: 0;

			}
		}
	}

	.inp {
		
	}

	.icon {
		position: absolute;
		right: -8rpx;
		bottom: 10rpx;
		border-radius: 50%;
		/* box-shadow: 1px 1px 1px 1px #ddd; */
	}
	.lis{

	}
	.line {
		border: 1px solid #ddd;
		width: 65rpx;
		position: relative;
		left: 50rpx;
		transform: rotate(90deg);
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

	.rights {
		position: relative;
		right: -60rpx;
		top: -20rpx;
	}
</style>
