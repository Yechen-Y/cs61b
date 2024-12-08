public class LinkedListDeque<T> {
    /*结点类*/
    public class IntNode{
        /**
         * 有前结点、后结点、结点储存的值
         */
        public IntNode pre;
        public IntNode next;
        public T item;

        public IntNode(IntNode pre, T item, IntNode next) {
            this.pre = pre;
            this.item = item;
            this.next = next;
        }
    }

    /**
     * 前哨兵、后哨兵、链表的大小
     */
    private IntNode sentFront;
    private IntNode sentBack;
    public int size;


    /**
     * DLList的构造函数，构造非空链表
     * @param item 第一个结点储存的值
     * @param size 链表的大小设置为1
     */
    public LinkedListDeque(T item){
        size = 1;
        sentFront = new IntNode(null,null, null);
        sentBack = new IntNode(null,null, null);

        IntNode first = new IntNode(sentFront, item, sentBack);
        sentFront.next = first;
        sentBack.pre = first;
    }

    /**
     * DLList的构造函数，构造空链表，前哨兵和哨兵互相引用
     */
    public LinkedListDeque(){
        size = 0;
        sentFront = new IntNode(null,null, null);
        sentBack = new IntNode(null,null, null);

        sentFront.next = sentBack;
        sentBack.pre = sentFront;
    }

    /**
     * 增加一个结点到DLList第一个位置（前哨兵前面），同时链表的size加一
     * @param item 增加到最前面结点的值
     */
    public void addFirst(T item){
        size += 1;
        IntNode first = new IntNode(sentFront, item, sentFront.next);
        sentFront.next.pre = first;
        sentFront.next = first;
    }

    /**
     * 增加一个结点到DLList的最后一个位置（后哨兵前面），同时链表的size加一
     * @param item 增加结点的值
     */
    public void addLast(T item){
        size += 1;
        IntNode last = new IntNode(sentBack.pre, item, sentBack);
        sentBack.pre.next = last;
        sentBack.pre = last;
    }

    /**
     * 检查链表是否为空链表
     * @return boolean 若链表为空则返回true，反之
     */
    public boolean isEmpty(){
        if (size == 0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 返回DLList的大小
     * @return size DLList的大小
     */
    public int size(){
        return size;
    }

    /**
     * 打印链表中每个结点的值 并以空格分开
     * @param ptr 为IntNode，作为辅助指针
     */
    public void printDeque(){
        IntNode ptr = sentFront;
        while (ptr.next != sentBack){
            System.out.print(ptr.next.item);
            System.out.print(" ");
            ptr = ptr.next;
        }
    }

    /**
     * 删除链表的第一个结点，并返回它的值，如果不存在则返回null
     * @return 删除结点的值
     */
    /* 被删除的结点要被回收 */
    public T removeFirst(){
        if (isEmpty()){
            return null;
        }else{
            T result = sentFront.next.item;
            size -= 1;
            sentFront.next = sentFront.next.next;
            sentFront.next.pre = sentFront;
            return result;
        }
    }

    /**
     * 删除DLList的最后一个结点，并返回其值，如果不存在则返回null
     * @return 删除结点的值
     */
    public T removeLast(){
        if (isEmpty()){
            return null;
        }else{
            T result = sentBack.pre.item;
            size -= 1;
            sentBack.pre = sentBack.pre.pre;
            sentBack.pre.next = sentBack;
            return result;
        }
    }

    /**
     * 使用迭代获取index位置结点的值，如果不存在该位置的结点，则返回null。第一个位置的结点inded为0.
     * @param index 获取index位置的结点
     * @return 返回该位置结点的值
     */
    public T get(int index){
        if (index > size - 1){
            return null;
        }else{
            IntNode ptr = sentFront;
            while (index >= 0){
                index -= 1;
                ptr = ptr.next;
            }
            return ptr.item;
        }
    }

    /**
     * 使用递归获取index位置结点的值
     * @param index 获取index位置的结点
     * @return 该结点的值
     */
    public T getRecursive(int index){
        if (index > size -1){
            return null;
        }else{
            return getRecursive_help(index, sentFront.next);
        }
    }

    /**
     * 递归辅助函数，用于跟踪DLList中的IntNode
     * @param index 位置
     * @param i 辅助结点
     * @return 结点的值
     */
    private T getRecursive_help(int index, IntNode i){
        if (index == 0){
            return i.item;
        }else{
            return getRecursive_help(index - 1, i.next);
        }
    }
}