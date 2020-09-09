<template>
	<view class="contact-info min-height-100 page-bg">
		<a-navbar title="联系信息" @back="$tool.uniRedirectTo({url:'/pages/home/personalInfo/index'})"></a-navbar>
		<view class="content">
			<u-gap height="12"></u-gap>
			<template v-for="(item,index) in list">
				<a-section :item="item" :key='index' :class="[index !==3 && index !==7 &&'border-bottom']"
					 @select="select" :isArrow='item.arrow'></a-section>		
			
				<u-gap height="10" v-if="index ==3"></u-gap>
			</template>
		</view>
	</view>
</template>

<script>
	export default{
		data(){
			return{
				list:[
					{
						name:"邮箱",
						info:"xx@qq.com",
						arrow:false
					},{
						name:"联系方式",
						info:"112",
						arrow:false
					},{
						name:"短号",
						info:"无",
						arrow:true,
						path:""
					},{
						name:"邮编",
						info:"076484",
						arrow:true,
						path:""
					},{
						name:"户口所在地",
						info:"陕西西安",
						arrow:false
					},{
						name:"现住址",
						info:"陕西西安",
						arrow:false
					}
				]
			}
		}
	}
</script>

<style scoped lang="scss">
.contact-info{
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
}
</style>
