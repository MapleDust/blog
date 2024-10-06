<script setup lang="ts">
import {ref} from "vue";
import axios from "axios";

const base64Text = ref("");
const base64Code = ref("");
const base64HasError = ref(false)
const base64ErrorTitle = ref("")
const base64TextButton = ref("primary")

/**
 * base64加密
 */
function base64Encryption() {
  //发送get请求
  axios.get("http://localhost:8088/base64Encryption", {
    params: {
      text: base64Text.value
    }
  }).then(res => {
    // 从后端返回的数据
    let data = res.data
    // 如果后端返回的状态码是错误的
    if (data.state !== 200) {
      // 将错误提示显示出来
      base64HasError.value = true
      // 设置错误提示的内容
      base64ErrorTitle.value = data.message
      // 将按钮设置为红色
      base64TextButton.value = "danger"
    }
    // 如果后端返回的状态码是正确的
    if (data.state === 200) {
      // 将错误提示隐藏
      base64HasError.value = false
      // 把结果直接显示到base64编码的输入框内
      base64Code.value = data.data
      // 将文本按钮设置成绿色
      base64TextButton.value = "success"
    }
  })
}
</script>

<template>
  <el-row :gutter="20" style="margin-top: 10px">
    <el-col :span="1"></el-col>
    <el-col :span="22">
      <el-card>
        <el-alert v-show="base64HasError" :title="base64ErrorTitle" :span="24" type="error" style="margin-bottom: 15px"
                  effect="dark"/>
        <template #header>Base64加解密</template>
        <el-input v-model="base64Text" placeholder="请输入要加密的文本" type="text"
                  style="width: auto;border-radius: 0;"/>
        <el-button @click="base64Encryption" :type="base64TextButton" :span="1">加密</el-button>
        <br>
        <div style="margin-top: 20px">
          <el-input v-model.lazy="base64Code" placeholder="请输入要解密的文本" type="text"
                    style="width: auto;border-radius: 0;"/>
          <el-button type="primary">解密</el-button>
        </div>
      </el-card>
    </el-col>
    <el-col :span="1"></el-col>
  </el-row>
</template>

<style scoped>

</style>