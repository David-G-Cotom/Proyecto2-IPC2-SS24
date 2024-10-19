export class Comentario {

    contenido: string;
    comentarista: number;
    revista: number;

    constructor(contenido: string, comentarista: number, revista: number) {
        this.contenido = contenido;
        this.comentarista = comentarista
        this.revista = revista;
    }

}