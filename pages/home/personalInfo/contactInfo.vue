<template>
	<view class="contact-info min-height-100 page-bg">
		<a-navbar title="联系信息" @back="$tool.uniRedirectTo({url:'/pages/home/personalInfo/index'})"></a-navbar>
		<view class="content">
			<u-gap height="12"></u-gap>
			<u-form class="form" :model="form" ref="uForm" v-if="form" >
				<u-form-item class="bg" label-width='150' label-align='rigth' label="联系电话">
					<view>{{form.dianhua}}</view>
				</u-form-item>
				<u-form-item class="bg" label-width='150' label-align='rigth' label="邮编">
					<u-input v-model="form.yzbm" :disabled="!isEdit" placeholder='' />
				</u-form-item>
				<!-- <u-form-item class="bg" label-width='150' label-align='rigth' label="户口性质">
					<u-input :selectOpen="hkxingzhi" type="select" v-model="form.hkxingzhi" @click='showSelect("hkxingzhi")' />
					<u-select v-model="hkxingzhi" :list="form.hkxingzhi_list" @confirm="confirm1"></u-select>
				</u-form-item> -->
				<u-form-item class="bg" label-width='150' label-align='rigth' label="家庭住址">
					<u-input v-model="form.jtzz" :disabled="!isEdit" placeholder='' />
				</u-form-item>
				<u-form-item class="bg" label-width='150' label-align='rigth' label="现住址">
					<u-input v-model="form.xzz" :disabled="!isEdit" placeholder='' />
				</u-form-item>
			</u-form>
			<view class="btn-wrap">
				<!-- <u-button type="success" class="btn" v-if="!isEdit" @click="isEdit=true">修改</u-button> -->
				<u-button type="success" class="btn" @click="submit">提交</u-button>
				<!-- <u-button v-if="isEdit" @click="isEdit=false">取消</u-button> -->
			</view>
		</view>
	</view>
</template>

<script>
	export default{
		data(){
			return{
				isEdit:true,
				form:null,
				hkxingzhi:false
			}
		},
		onLoad(){
			this.init()
		},
		methods:{
			init(){
				this.$tool.uniRequest({
					url:"rsdangan/lianxi/",
					method:'GET',
					params:{
						id:this.$tool.uniGetStorage('userId')
					},
					success:(res)=>{
						this.form=res
					}
				})	
			},
			confirm1(e) {
				this.form.hkxingzhi=e[0].label
				this.form.hkxingzhiId=e[0].value
			},
			showSelect(type){
				if(!this.isEdit) return
				this[type]=true
			},
			submit(){
				let params= this.$u.deepClone(this.form)
				delete params.hkxingzhi
				delete params.dianhua
				params.id=this.$tool.uniGetStorage('userId')
				this.$tool.uniRequest({
					url: "rsdangan/bianji/",
					method: 'POST',
					params,
					success: (res) => {
						this.$tool.uniShowToast({
							title:"修改成功！"
						})
						this.init()
						//this.isEdit=false
					}
				})
			},
		}
	}
</script>

<style scoped lang="scss">
.contact-info{
	padding:20rpx;
	.header{
		height: 240rpx;
		.name{
			margin-top: 14rpx;
			font-size: 30rpx;
		}
	}
	.content{
		.line{
			height: 80rpx;
			padding: 0 20rpx;
		}
	}
	.btn-wrap {
		padding: 40rpx 200rpx;
	
		.btn {
			margin-bottom: 40rpx;
		}
	}
	.form {
		border-radius: 20rpx;
		overflow: hidden;
	
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
					}
				}
			}
	
			.label {
				width: 200rpx;
				text-align: right;
				margin-right: 30rpx;
			}
	
			.textarea {
				padding: 20rpx 30rpx;
			}
		}
	
	}
	
}
</style>
