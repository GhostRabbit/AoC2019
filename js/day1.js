var fs = require('input.js')


module.exports = {
    calc: (mass) => {
        let fuel = Math.floor (mass / 3) - 2
        return fuel <= 0 ? 0 : fuel + module.exports.calc(fuel)
    },
    
    sumCalc: (masses) => {
        console.log('m: '+ masses)
        return masses.map(module.exports.calc).reduce((a, b) => a + b, 0)
    }
}

let input = fs.readNumbers('day1.input')
console.log(module.exports.sumCalc(input))
