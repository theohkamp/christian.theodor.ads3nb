use std::ptr;

struct Node<T> {
    value: T,
    next: Option<Box<Node<T>>>,
}

pub struct Queue<T> {
    front: Option<Box<Node<T>>>,
    back: *mut Option<Box<Node<T>>>,
    length: usize,
}

impl<T> Queue<T> {
    // Cria uma nova fila vazia
    pub fn new() -> Self {
        Queue {
            front: None,
            back: ptr::null_mut(),
            length: 0,
        }
    }

    // Enfileira um elemento na fila
    pub fn enqueue(&mut self, elem: T) {
        let new_node = Box::new(Node {
            value: elem,
            next: None,
        });

        // Se a fila estiver vazia, o novo é o primeiro e o último
        if self.length == 0 {
            self.front = Some(new_node);
            self.back = Box::into_raw(Box::new(Some(new_node)));
        } else {
            unsafe {
                // Acessa o último e atualiza o ponteiro para o próximo
                (*self.back).as_mut().unwrap().next = Some(new_node);
                self.back = Box::into_raw(Box::new(Some(new_node)));
            }
        }
        self.length += 1;
    }

    // Desenfileira um elemento da fila
    pub fn dequeue(&mut self) -> Option<T> {
        if let Some(front_node) = self.front.take() {
            self.front = front_node.next;
            self.length -= 1;
            if self.length == 0 {
                self.back = ptr::null_mut();
            }
            Some(front_node.value)
        } else {
            None
        }
    }

    // Retorna o elemento da frente sem removê-lo
    pub fn peek(&self) -> Option<&T> {
        self.front.as_ref().map(|node| &node.value)
    }

    // Retorna o número de elementos na fila
    pub fn len(&self) -> usize {
        self.length
    }
}

// Implementação de `Drop` para garantir que todos sejam desalocados corretamente
impl<T> Drop for Queue<T> {
    fn drop(&mut self) {
        while let Some(_) = self.dequeue() {
            // Desalocando todos da fila
        }
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_enqueue_dequeue() {
        let mut queue: Queue<i32> = Queue::new();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        
        assert_eq!(queue.dequeue(), Some(1));
        assert_eq!(queue.dequeue(), Some(2));
        assert_eq!(queue.dequeue(), Some(3));
        assert_eq!(queue.dequeue(), None);
    }

    #[test]
    fn test_peek() {
        let mut queue: Queue<i32> = Queue::new();
        queue.enqueue(1);
        queue.enqueue(2);
        
        assert_eq!(queue.peek(), Some(&1));
        
        queue.dequeue();
        assert_eq!(queue.peek(), Some(&2));
    }

    #[test]
    fn test_len() {
        let mut queue: Queue<i32> = Queue::new();
        
        assert_eq!(queue.len(), 0);
        
        queue.enqueue(1);
        queue.enqueue(2);
        
        assert_eq!(queue.len(), 2);
        
        queue.dequeue();
        assert_eq!(queue.len(), 1);
        
        queue.dequeue();
        assert_eq!(queue.len(), 0);
    }
}