<template>
	<view class="a-navbar">
		<u-navbar :title="title" :background="{backgroundColor: backgroundColor}" :border-bottom='false' :is-back="isBack"
		 :back-icon-color='backIconColor' title-color='#000'  :custom-back='customBack' :height="50"></u-navbar>
	</view>
</template>

<script>
	export default{
		props:{
			title:[String],
			isBack:{
				type:Boolean,
				default:true
			},
			backgroundColor:{
				type:String,
				default:"#EDEDED"
			},
			backIconColor:{
				type:String,
				default:"#000"
			}
		},
		methods:{
			customBack(){
				this.$emit("back")			
			}
		}
	}
</script>

<style scoped lang="scss">
</style>
