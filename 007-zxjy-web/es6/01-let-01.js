// var 声明的变量没有局部作用域

// let 声明的变量  有局部作用域

{
    var a = 0
    var b = 0

}
console.log(a);

console.log(b); // ReferenceError: b is not defined