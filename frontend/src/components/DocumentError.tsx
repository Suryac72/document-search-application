import type { ErrorMessage as ErrorMessageProps } from "../models/types";
/**
 * Error Component --> Renders if there is some error during fetching search results 
 */
export const DocumentError = ({ errorMessage }: ErrorMessageProps) => {
  return (
    <div className="bg-red-50 border-l-4 border-red-500 p-4 mb-6 rounded">
      <p className="text-red-700">{errorMessage}</p>
    </div>
  );
};
