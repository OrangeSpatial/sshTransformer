import {
  Button, Form, Input, Select, Table
} from 'ant-design-vue'
import 'ant-design-vue/dist/antd.css'

const components = [
  Button,
  Form,
  Input,
  Select,
  Table
]

export function setupAntd (app) {
  components.forEach(component => {
    app.use(component)
  })
}
