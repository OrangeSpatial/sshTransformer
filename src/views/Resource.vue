<template>
  <div class="resource">
    <a-table :columns="columns" :data-source="data"  :row-key="record=>record.id">
<!--      <template #action="{record}">-->
<!--        <a @click="delNode(record.id)">删除</a>-->
<!--      </template>-->
<!--      <template #expandedRowRender="{ record }">-->
<!--        <p style="margin: 0">-->
<!--          {{ record.description }}-->
<!--        </p>-->
<!--      </template>-->
    </a-table>
  </div>
</template>

<script>
// @ is an alias to /src
import { getCurrentInstance, onMounted, ref } from 'vue'
// import { message } from 'ant-design-vue'

const columns = [
  {
    title: '文件名',
    dataIndex: 'name',
    key: 'name'
  },
  {
    title: '文件路径',
    dataIndex: 'path',
    key: 'path'
  },
  {
    title: '文件大小',
    dataIndex: 'size',
    key: 'size'
  }
  // {
  //   title: '用户名',
  //   dataIndex: 'user',
  //   key: 'user'
  // },
  // {
  //   title: '操作',
  //   dataIndex: '',
  //   key: 'x',
  //   slots: {
  //     customRender: 'action'
  //   }
  // }
]

export default {
  name: 'Home',
  components: {
  },
  setup () {
    const { proxy } = getCurrentInstance()

    const data = ref([])

    const getAllFiles = () => {
      proxy.$http.get('/file/all').then(res => {
        console.log(res.data)
        data.value = res.data
      })
    }

    onMounted(() => {
      getAllFiles()
    })

    return {
      columns,
      data
    }
  }
}
</script>

<style lang="less" scoped>
.resource{
  width: 860px;
  margin: 0 auto;
}
</style>
