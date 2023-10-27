import java.io.File

fun main(args: Array<String>) {
    print("Enter a file name: ")
    var fileName = readLine()
    var file = File(fileName)

    if (!file.exists()) {
        println("Error opening file: $fileName")
        System.exit(1)
    } else {
        var graphNodes = mutableMapOf<String, Node>()
        var combos = mutableMapOf<String, Int>()
        var node1: Node
        var node2: Node
        file.forEachLine{
            var splitLine = it.split("->")
            //println(splitLine[0] + "\t" + splitLine[1])
            var colorOne = splitLine[0].split("_")
            var colorTwo = splitLine[1].split("_")

            node1 = Node(splitLine[0], colorOne[1])
            node1.color = colorOne[0]
            node2 = Node(splitLine[1], colorTwo[1])
            node2.color = colorTwo[0]

            if(!graphNodes.containsKey(splitLine[0])){
                node1.map[node2.fullColor] = node2.id
                graphNodes[splitLine[0]] = node1
            }else{
                var currentMap = graphNodes[splitLine[0]]
                currentMap?.map?.put(node2.fullColor, node2.id)
            }


            if(!graphNodes.containsKey(splitLine[1])) {
                graphNodes[splitLine[1]] = node2
            }

            var combo = node1.color + " -> " + node2.color
            if(!combos.containsKey(combo)){
                combos[combo] = 1
            }else{
                combos[combo] = combos[combo]!! + 1
            }

        }
        println("==== GRAPH ====")
        for((name, g) in graphNodes){
            println("${g.color} ${g.id}:")
            for((fullColor, id) in g.map){
                var color = fullColor.split("_")[0]
                println("\t=> $color $id")
            }
        }
        println("==== COLOR COMBINATIONS ====")
        for((color, count) in combos){
            println("$color\t $count")
        }
    }
}