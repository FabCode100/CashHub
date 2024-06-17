import { Text, TouchableOpacity} from "react-native"

type Props = {
    title: string
}

export function Button({title}: Props){
    return(
        <TouchableOpacity>
          <Text>{title}</Text>
        </TouchableOpacity>
    )
}

