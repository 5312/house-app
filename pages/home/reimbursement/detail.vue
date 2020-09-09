<template>
	<view class="detail page-bg min-height-100">
		<a-navbar title="报账详情" @back="$tool.uniRedirectTo({url:'/pages/home/reimbursement/index'})"></a-navbar>	
		<view class="content bg-white">
			<u-form ref="uForm" label-width='140' class="form" :model="form">
				<u-form-item label="房源地址:">
					<u-input v-model="form.address" />
				</u-form-item>
				<u-form-item label="业主姓名:">
					<u-input v-model="form.name1" />
				</u-form-item>
				<u-form-item label="客户姓名:">
					<u-input v-model="form.name2" />
				</u-form-item>
				<u-form-item label="成交总价:">
					<u-input v-model="form.value1" />
				</u-form-item>
				<u-form-item label="分成业绩:">
					<u-input v-model="form.value2" />
				</u-form-item>
				<u-form-item label="交易归属:">
					<u-input v-model="form.ace" />
				</u-form-item>
				<u-form-item label="交易类型:">
					<u-input v-model="form.type" />
				</u-form-item>
			</u-form>
		</view>
	</view>
</template>

<script>
	export default{
		data(){
			return{
				form:{
					adress:"",
					name1:"",
					name2:"",
					value1:"",
					value2:"",
					ace:"",
					type:''
				}
			}
		}
	}
</script>

<style scoped lang="scss">
	.detail{
		.content{
			.form{
				padding:20rpx 40rpx;
			}
		}
	}
</style>
