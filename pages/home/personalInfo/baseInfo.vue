<template>
	<view class="personal-base-info min-height-100 page-bg">
		<a-navbar title="基本信息" @back="$tool.uniRedirectTo({url:'/pages/home/personalInfo/index'})"></a-navbar>
		<view class="content">
			<u-gap height="10"></u-gap>
			<u-form class="form" :model="form" ref="uForm" v-if="form">
				<u-form-item class="bg col" label-width='150' label-align='rigth'  label="姓名">
					<view>{{form.ygmingcheng}}</view>
				</u-form-item>
				<u-form-item class="bg" label-width='150' label-align='rigth' label="工号">
					<view>{{form.ygbianhao}}</view>
				</u-form-item>
				<u-form-item class="bg" label-width='150' label-align='rigth' label="性别">
					<u-radio-group class="bg  flex" v-model="form.xingbie" :disabled="!isEdit">
						<u-radio v-for="(item, index) in listSex" :key="index" :name="item.value" :label-disabled="true">
							{{item.name}}
						</u-radio>
					</u-radio-group>
				</u-form-item>
				<u-form-item class="bg" label-width='150' label-align='rigth' label="籍贯">
					<u-input v-model="form.jiguan" :disabled="!isEdit" placeholder='' />
				</u-form-item>
				<u-form-item class="bg" label-width='150' label-align='rigth' label="民族">	
					<u-input :selectOpen="minzu" type="select" v-model="form.minzu" @click='showSelect("minzu")' />
					<u-select v-model="minzu" :list="form.minzu_list" @confirm="confirm1"></u-select>
				</u-form-item>
				<u-form-item class="bg" label-width='150' label-align='rigth' label="学历">
					<u-input :selectOpen="xueli" type="select" v-model="form.xueli" @click='showSelect("xueli")' />
					<u-select v-model="xueli" :list="form.xueli_list" @confirm="confirm2"></u-select>
				</u-form-item>
				<u-form-item class="bg" label-width='150' label-align='rigth' label="婚姻状况">
					<u-input :selectOpen="hunyin" type="select" v-model="form.hunyin" @click='showSelect("hunyin")' />
					<u-select v-model="hunyin" :list="form.hunyin_list" @confirm="confirm3"></u-select>
				</u-form-item>
				<u-form-item class="bg" label-width='150' label-align='rigth' label="政治面貌">

					<u-input :selectOpen="zzmianmao" type="select" v-model="form.zzmianmao" @click='showSelect("zzmianmao")' />
					<u-select v-model="zzmianmao" :list="form.mianmao_list" @confirm="confirm4"></u-select>
				</u-form-item>
				<u-form-item class="bg" label-width='150' label-align='rigth' label="出生年月">
					<u-input :selectOpen="chushengrq" v-model="form.chushengrq" type="select" @click='showSelect("chushengrq")' />
					<u-calendar v-model="chushengrq" mode="date" @change="calendarChange"></u-calendar>
				</u-form-item>
				<u-form-item class="bg" label-width='150' label-align='rigth' label="身份证号">
					<u-input v-model="form.sfzh"type="number" />
					<!-- <u-keyboard :mask="false" ref="uKeyboard" @change="valChange" @backspace="backspace" mode="number" v-model="keyNum"></u-keyboard> -->
				</u-form-item>
			</u-form>
			<view class="btn-wrap">
				<u-button type="success" class="btn"  @click="submit">修改</u-button>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				keyNum:false,
				isEdit: true,
				minzu:false,
				xueli:false,
				hunyin:false,
				zzmianmao:false,
				chushengrq:false,
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
				form: {

				},
			}
		},
		onLoad() {
			this.init()
		},
		methods: {
			// 按键被点击(点击退格键不会触发此事件)
			valChange(val) {
				// 将每次按键的值拼接到value变量中，注意+=写法
				this.form.sfzh += val;
				console.log(this.sfzh);
			},
			// 退格键被点击
			backspace() {
				// 删除value的最后一个字符
				if (this.form.sfzh.length) this.form.sfzh = this.form.sfzh.substr(0, this.form.sfzh.length - 1);
				console.log(this.form.sfzh);
			},
			init() {
				this.$tool.uniRequest({
					url: "rsdangan/jiben",
					method: 'GET',
					params: {
						id: this.$tool.uniGetStorage('userId')
					},
					success: (res) => {
						this.form = res
						this.form.minzu_list.forEach(item=>{
							item.label=item.lxming
							item.value=item.xsshunxu
							if(item.id===this.form.minzu){
								this.form.minzu=item.lxming
								this.form.minzuId=item.id
							}
						})
						this.form.hunyin_list.forEach(item=>{
							item.label=item.lxming
							item.value=item.xsshunxu
							if(item.id===this.form.hunyin){
								this.form.hunyin=item.lxming
								this.form.hunyinId=item.id
							}  
						})
						this.form.mianmao_list.forEach(item=>{
							item.label=item.lxming
							item.value=item.xsshunxu
							if(item.id===this.form.zzmianmao){
								this.form.zzmianmao=item.lxming
								this.form.zzmianmaoId=item.id
							}
						})
						this.form.xueli_list.forEach(item=>{
							item.label=item.lxming
							item.value=item.xsshunxu
							if(item.id===this.form.xueli){
								this.form.xueli=item.lxming
								this.form.xueliId=item.id
							}
						})
						console.log(this.form)
					}
				})
			},
			confirm1(e) {
				this.form.minzu=e[0].label
				this.form.minzuId=e[0].value
			},
			confirm2(e) {
				this.form.xueli=e[0].label
				this.form.xueliId=e[0].value
			},
			confirm3(e) {
				this.form.hunyin=e[0].label
				this.form.hunyinId=e[0].value
			},
			confirm4(e) {
				this.form.zzmianmao=e[0].label
				this.form.zzmianmaoId=e[0].value
			},
			showSelect(type){
				if(!this.isEdit) return
				this[type]=true
			},
			calendarChange(e){
				this.form.chushengrq=e.result
			},
			submit(){
				let params= this.$u.deepClone(this.form)
				
				delete params.minzu_list
				delete params.xueli_list
				delete params.hunyin_list
				delete params.mianmao_list
				params.minzu=params.minzuId || ''
				params.xueli=params.xueliId || ''
				params.hunyin=params.hunyinId || ''
				params.zzmianmao=params.zzmianmaoId || ''
				console.log(params)
				this.$tool.uniRequest({
					url: "rsdangan/bianji/",
					method: 'POST',
					params,
					success: (res) => {
						this.$tool.uniShowToast({
							title:"修改成功！"
						})
						//this.isEdit=false
						this.init()
					}
				})
			},
			select(item) {
				console.log(item)
			}
		}
	}
</script>

<style scoped lang="scss">

	.personal-base-info {
		padding:20rpx;
		.form {
			border-radius: 20rpx;
			overflow: hidden;

			.bg {
				background: #fff;
				padding-right: 20rpx;
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

		.header {
			height: 240rpx;

			.name {
				margin-top: 14rpx;
				font-size: 30rpx;
			}
		}

		.content {
			padding-top: 2rpx;

			.line {
				height: 80rpx;
				padding: 0 20rpx;
			}

			.btn-wrap {
				padding: 40rpx 200rpx;

				.btn {
					margin-bottom: 40rpx;
				}
			}
		}
	}
</style>
