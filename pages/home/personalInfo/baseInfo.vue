<template>
	<view class="personal-base-info min-height-100 page-bg">
		<a-navbar title="基本信息" @back="$tool.uniRedirectTo({url:'/pages/home/personalInfo/index'})"></a-navbar>
		<view class="content">
			<u-gap height="10"></u-gap>
			<template v-for="(item,index) in list">
				<a-section :item="item" :key='index' :class="[index !==3 && index !==7 &&'border-bottom']"
					 @select="select" :isArrow='item.arrow'></a-section>				
				<u-gap height="10" v-if="index ==3 || index ===7"></u-gap>
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
						name:"姓名",
						info:"刘明",
						arrow:false
					},{
						name:"曾用名",
						info:"无",
						arrow:true,
						path:""
					},{
						name:"性别",
						info:"男",
						arrow:true,
						path:""
					},{
						name:"工号",
						info:"076484",
						arrow:false,
					},{
						name:"籍贯",
						info:"汉",
						arrow:true,
						path:""
					},{
						name:"民族",
						info:"汉族",
						arrow:true,
						path:""
					},{
						name:"身高(cm)",
						info:"180",
						arrow:true,
						path:""
					},{
						name:"体重(kg)",
						info:"180",
						arrow:true,
						path:""
					},{
						name:"年龄",
						info:"30",
						arrow:true,
						path:""
					},{
						name:"出身日期",
						info:"1990-2-20",
						arrow:true,
						path:""
					},{
						name:"身份证号码",
						info:"076484",
						arrow:false,
					}
				]
			}
		},
		methods:{
			select(item){
				console.log(item)
			}
		}
	}
</script>

<style scoped lang="scss">
.personal-base-info{
	.header{
		height: 240rpx;
		.name{
			margin-top: 14rpx;
			font-size: 30rpx;
		}
	}
	.content{
		padding-top: 2rpx;
		.line{
			height: 80rpx;
			padding: 0 20rpx;
		}
	}
}
</style>
