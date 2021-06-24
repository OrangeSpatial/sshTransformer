<template>
  <div class="task">
    <a-form
      layout="inline"
      :model="formState"
      @finish="handleFinish"
      @finishFailed="handleFinishFailed"
    >
      <a-form-item>
        <a-input v-model:value="formState.name" placeholder="任务名称"/>
      </a-form-item>
      <a-form-item>
        <a-input v-model:value="formState.localPath" placeholder="本地存储路径"/>
      </a-form-item>
      <a-form-item>
        <a-select
          v-model:value="formState.nodeId"
          style="width: 200px"
          placeholder="选择目标节点"
        >
          <a-select-option v-for="item in nodes" :key="item.id" >{{item.nodeName}}</a-select-option>
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
        <a-button type="primary" :disabled="record.status===1" @click="actionTask(record.id)">执行</a-button>
        <a-button type="danger" @click="delTask(record.id)">删除</a-button>
      </template>
      <template #status="{text}">
        {{text===0?"待执行":"正在执行"}}
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
    dataIndex: 'name',
    key: 'name'
  },
  {
    title: '本地路径',
    dataIndex: 'localPath',
    key: 'localPath'
  },
  {
    title: '节点名称',
    dataIndex: 'nodeId',
    key: 'nodeId'
  },
  {
    title: '任务状态',
    dataIndex: 'status',
    key: 'status',
    slots: {
      customRender: 'status'
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
      name: '',
      localPath: '',
      nodeId: null
    })

    const handleFinish = values => {
      console.log(values, formState)
      proxy.$http.post('/task/add', formState).then(res => {
        console.log(res.data)
        getAllTasks()
        message.success('添加成功！')
      })
    }

    const handleFinishFailed = errors => {
      console.log(errors)
    }

    const { proxy } = getCurrentInstance()

    const data = ref([])
    const nodes = ref([])

    const getAllTasks = () => {
      proxy.$http.get('/task/all').then(res => {
        console.log(res.data)
        data.value = res.data
      })
    }
    const getAllNodes = () => {
      proxy.$http.get('/node/all').then(res => {
        console.log(res.data)
        nodes.value = res.data
      })
    }

    const delTask = (id) => {
      proxy.$http.get('/task/del/' + id).then(res => {
        console.log(res.data)
        if (res.data) {
          getAllTasks()
          return message.success('删除成功！')
        }
      })
    }

    const actionTask = (id) => {
      proxy.$http.get('/task/action/' + id).then(res => {
        console.log(res.data)
        if (res.data) {
          getAllTasks()
          return message.success('执行成功！！')
        }
      })
    }

    onMounted(() => {
      getAllTasks()
      getAllNodes()
    })

    return {
      formState,
      handleFinish,
      handleFinishFailed,
      columns,
      data,
      nodes,
      getAllTasks,
      delTask,
      actionTask
    }
  }
}
</script>

<style lang="less" scoped>
.task{
  width: 860px;
  margin: 0 auto;
}
</style>
