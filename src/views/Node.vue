<template>
  <div class="node">
    <a-form
      layout="inline"
      :model="formState"
      @finish="handleFinish"
      @finishFailed="handleFinishFailed"
    >
      <a-form-item>
        <a-input v-model:value="formState.nodeName" placeholder="节点名称"/>
      </a-form-item>
      <a-form-item>
        <a-input v-model:value="formState.remotePath" placeholder="目标路径"/>
      </a-form-item>
      <a-form-item>
        <a-input v-model:value="formState.host" placeholder="主机IP"/>
      </a-form-item>
      <a-form-item>
        <a-input v-model:value="formState.user" placeholder="用户名"/>
      </a-form-item>
      <a-form-item>
        <a-input type="password" v-model:value="formState.password" placeholder="密码"/>
      </a-form-item>
      <a-form-item>
        <a-select
          v-model:value="formState.systemType"
          style="width: 200px"
          placeholder="选择主机系统类型"
        >
          <a-select-option :value="0">windows</a-select-option>
          <a-select-option :value="1">linux</a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item>
        <a-button
          type="primary"
          html-type="submit"
        >
          添加
        </a-button>
      </a-form-item>
    </a-form>
    <a-table :columns="columns" :data-source="data"  :row-key="record=>record.id">
      <template #action="{record}">
        <a @click="delNode(record.id)">删除</a>
      </template>
      <template #type="{text}">
        {{text===0?"Windows":"Linux"}}
      </template>
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
import { getCurrentInstance, onMounted, reactive, ref } from 'vue'
import { message } from 'ant-design-vue'

const columns = [
  {
    title: '任务名称',
    dataIndex: 'nodeName',
    key: 'nodeName'
  },
  {
    title: '目标路径',
    dataIndex: 'remotePath',
    key: 'remotePath'
  },
  {
    title: '主机IP',
    dataIndex: 'host',
    key: 'host'
  },
  {
    title: '用户名',
    dataIndex: 'user',
    key: 'user'
  },
  {
    title: '系统类型',
    dataIndex: 'systemType',
    key: 'systemType',
    slots: {
      customRender: 'type'
    }
  },
  {
    title: '操作',
    dataIndex: '',
    key: 'x',
    slots: {
      customRender: 'action'
    }
  }
]

export default {
  name: 'Home',
  components: {
  },
  setup () {
    const formState = reactive({
      nodeName: '',
      remotePath: '',
      host: '',
      user: '',
      password: '',
      systemType: null
    })

    const handleFinish = values => {
      console.log(values, formState)
      proxy.$http.post('/node/add', formState).then(res => {
        console.log(res.data)
        message.success('添加成功！')
        getAllNodes()
      })
    }

    const handleFinishFailed = errors => {
      console.log(errors)
    }

    const { proxy } = getCurrentInstance()

    const data = ref([])

    const getAllNodes = () => {
      proxy.$http.get('/node/all').then(res => {
        console.log(res.data)
        data.value = res.data
      })
    }

    const delNode = (id) => {
      proxy.$http.get('/node/del/' + id).then(res => {
        console.log(res.data)
        if (res.data) {
          getAllNodes()
          return message.success('删除成功！')
        }
      })
    }

    onMounted(() => {
      getAllNodes()
    })

    return {
      formState,
      handleFinish,
      handleFinishFailed,
      columns,
      data,
      getAllNodes,
      delNode
    }
  }
}
</script>

<style lang="less" scoped>
.node{
  width: 860px;
  margin: 0 auto;
}
</style>
