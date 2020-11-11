//  拓展运算符（...）用于取出参数对象所有可遍历属性然后拷贝到当前对象。

let person = {name:'clx',age:13}
let someone = person // 引用赋值
// let someone = {...person}   // 对象拷贝
someone.name = 'hth'
console.log(person)
console.log(someone)

// { name: 'clx', age: 13 }
// { name: 'hth', age: 13 }