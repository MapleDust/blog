<script setup lang="ts">
import {ref} from "vue";
import axios from "axios";
import {ElNotification} from "element-plus";

const base64Text = ref("");
const base64Code = ref("");
const base64HasError = ref(false)
const base64ErrorTitle = ref("")
const base64TextButton = ref("primary")
const base64CodeButton = ref("primary")

/**
 * base64加密
 */
function base64Encryption() {
  // 发送get请求
  axios.get("http://localhost:8088/base64Encryption", {
    params: {
      text: base64Text.value
    }
  }).then(res => {
    // 从后端返回的数据
    const data = res.data
    // 如果后端返回的状态码是错误的
    if (data.state !== 200) {
      // 将错误提示显示出来
      base64HasError.value = true
      // 设置错误提示的内容
      base64ErrorTitle.value = data.message
      // 将按钮设置为红色
      base64TextButton.value = "danger"
      // 将base64编码的按钮设置为普通
      base64CodeButton.value = "primary"
      // 将加密后的结果清空
      base64Code.value = null
    }
    // 如果后端返回的状态码是正确的
    if (data.state === 200) {
      // 将错误提示隐藏
      base64HasError.value = false
      // 把结果直接显示到base64编码的输入框内
      base64Code.value = data.data
      // 将文本按钮设置成绿色
      base64TextButton.value = "success"
      // 将base64编码的按钮设置为普通
      base64CodeButton.value = "primary"
    }
  }).catch(res => {
    console.debug(res)
    const status = res.code
    console.debug(status)
    if (status === "ERR_NETWORK") {
      // 将文本按钮设置为红色
      base64TextButton.value = "danger"
      // 将base64按钮设置为普通
      base64CodeButton.value = "primary"
      // 清空base64加密结果
      base64Code.value = null
      // 将错误提示隐藏
      base64HasError.value = false
      // 弹出提示框
      ElNotification({
        title: '错误',
        message: '服务器内部错误，请联系管理员',
        type: 'error',
        duration: 5 * 1000
      })
    }
  })
}

/**
 * Base64解密
 */
function base64Decryption() {
  axios.get("http://localhost:8088/base64Decryption", {
    params: {
      base64: base64Code.value
    }
  }).then(res => {
    const data = res.data
    if (data.state !== 200) {
      // 将错误提示显示出来
      base64HasError.value = true
      // 设置错误提示的内容
      base64ErrorTitle.value = data.message
      // 将Base64编码按钮设置成红色
      base64CodeButton.value = "danger"
      // 将文本的按钮设置为普通
      base64TextButton.value = "primary"
      // 将加密前的结果清空
      base64Text.value = null
    }
    if (data.state === 200) {
      // 将错误提示隐藏
      base64HasError.value = false
      // 把结果直接显示到文本输入框内
      base64Text.value = data.data
      // 将Base64编码按钮设置成绿色
      base64CodeButton.value = "success"
      // 将文本的按钮设置为普通
      base64TextButton.value = "primary"
    }
  }).catch(res => {
    const code = res.code
    if (code === "ERR_NETWORK") {
      // 将错误提示隐藏
      base64HasError.value = false
      // 将base64按钮设置为红色
      base64CodeButton.value = "danger"
      // 将文本按钮设置为普通
      base64TextButton.value = "primary"
      // 清空base64解密后的文本内容
      base64Text.value = null
      // 弹出提示框
      ElNotification({
        title: '错误',
        message: '服务器内部错误，请联系管理员',
        type: 'error',
        duration: 5 * 1000
      })
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
        <el-row>
          <el-col :span="20">
            <el-input v-model="base64Text" placeholder="请输入要加密的文本" type="text"/>
          </el-col>
          <el-col :span="4">
            <el-button @click="base64Encryption" :type="base64TextButton">加密</el-button>
          </el-col>
        </el-row>
        <br>
        <el-row>
          <el-col :span="20">
            <el-input v-model="base64Code" placeholder="请输入要解密的文本" type="text"/>
          </el-col>
          <el-col :span="4">
            <el-button @click="base64Decryption" :type="base64CodeButton">解密</el-button>
          </el-col>
        </el-row>
      </el-card>
    </el-col>
    <el-col :span="1"></el-col>
  </el-row>
</template>

<style scoped>

</style>