use std::io;

fn reverse_string(s: &str) -> String {
    s.chars().rev().collect()
}

fn main() {
    let mut input = String::new();
    println!("Digite uma string para inverter:");
    
    io::stdin().read_line(&mut input).expect("Erro ao ler a entrada");
    
    let input = input.trim(); // Remove espaços extras e quebras de linha
    let reversed = reverse_string(input);

    println!("String invertida: {}", reversed);
}